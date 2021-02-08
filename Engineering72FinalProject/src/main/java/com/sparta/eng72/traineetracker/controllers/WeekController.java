package com.sparta.eng72.traineetracker.controllers;

import com.sparta.eng72.traineetracker.entities.*;
import com.sparta.eng72.traineetracker.services.*;
import com.sparta.eng72.traineetracker.utilities.Pages;
import com.sparta.eng72.traineetracker.utilities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WeekController {

    private final CourseGroupService courseGroupService;
    private final TraineeService traineeService;
    private final WeekReportService weekReportService;
    private final CourseService courseService;

    @Autowired
    public WeekController(CourseGroupService courseGroupService, TraineeService traineeService, WeekReportService weekReportService, CourseService courseService) {
        this.courseGroupService = courseGroupService;
        this.traineeService = traineeService;
        this.weekReportService = weekReportService;
        this.courseService = courseService;
    }

    @GetMapping("/trainer/newWeek")
    public ModelAndView newWeekPage(ModelMap modelMap){
        modelMap.addAttribute("allGroups", courseGroupService.getAllCourseGroups());
        return new ModelAndView(Pages.accessPage(Role.TRAINER, Pages.TRAINER_ADD_WEEK_PAGE), modelMap);
    }

    @PostMapping("/trainer/addNewWeek")
    public ModelAndView getNewWeek(@RequestParam String groupId, ModelMap modelMap){
        modelMap.addAttribute("allGroups", courseGroupService.getAllCourseGroups());

        int groupID = Integer.parseInt(groupId);
        CourseGroup group = getGroup(Integer.parseInt(groupId));
        int courseDuration = getCourse(group).getDuration();
        int week_num = courseGroupService.getWeekByGroupId(groupID);

        if (week_num == Integer.MIN_VALUE || week_num >= courseDuration) {
            String error = "" + group.getGroupName() + " is currently in the final week of the course!";
            modelMap.addAttribute("error", error);
            return new ModelAndView(Pages.accessPage(Role.TRAINER, Pages.TRAINER_ADD_WEEK_PAGE), modelMap);
        }

        courseGroupService.incrementWeek(groupID);

        List<WeekReport> weekReports = getWeekReports(groupID, week_num);
        weekReportService.createReports(weekReports);

        String success = "Course Week has changed to Week " + week_num +
                " for " + courseGroupService.getGroupByID(groupID).get().getGroupName();

        modelMap.addAttribute("success", success);
        return new ModelAndView(Pages.accessPage(Role.TRAINER, Pages.TRAINER_ADD_WEEK_PAGE), modelMap);
    }

    private WeekReport populateWeekReport(int week_num, Trainee trainee) {
        WeekReport weekReport = new WeekReport();
        weekReport.setTraineeId(trainee.getTraineeId());
        weekReport.setWeekNum(week_num);
        LocalDate deadlineDay = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        LocalDateTime deadline = deadlineDay.atTime(17, 30);
        weekReport.setDeadline(deadline);

        weekReport.setTrainerCompletedFlag((byte) 0);
        weekReport.setTraineeConsultantGradeFlag((byte) 0);
        weekReport.setTraineeContinueFlag((byte) 0);
        weekReport.setTraineeStartFlag((byte) 0);
        weekReport.setTraineeStopFlag((byte) 0);
        weekReport.setTraineeSubmittedFlag((byte) 0);
        weekReport.setTraineeTechnicalGradeFlag((byte) 0);

        weekReport.setConsultantGradeTrainer("");
        weekReport.setConsultantGradeTrainee("");
        weekReport.setTechnicalGradeTrainee("");
        weekReport.setTechnicalGradeTrainer("");

        weekReport.setOverallGradeTrainer("");

        weekReport.setMostRecentEdit(LocalDateTime.now());

        weekReport.setStartTrainee("");
        weekReport.setStartTrainer("");
        weekReport.setStopTrainee("");
        weekReport.setStopTrainer("");
        weekReport.setContinueTrainee("");
        weekReport.setContinueTrainer("");

        weekReport.setTrainerComments("");
        return weekReport;
    }

    private List<WeekReport> getWeekReports(int groupID, int week_num) {
        List<Trainee> trainees = traineeService.getTraineesByGroupId(groupID);
        List<WeekReport> weekReports = new ArrayList<>();
        for(Trainee trainee: trainees){
            WeekReport weekReport = populateWeekReport(week_num, trainee);
            weekReports.add(weekReport);
        }
        return weekReports;
    }

    private CourseGroup getGroup(Integer groupId) {
        CourseGroup group = null;
        if (courseGroupService.getGroupByID(groupId).isPresent()) {
            group = courseGroupService.getGroupByID(groupId).get();
        }
        return group;
    }

    private Course getCourse(CourseGroup group) {
        Course course = null;
        if(courseService.getCourseByID(group.getCourseId()).isPresent()){
            course = courseService.getCourseByID(group.getCourseId()).get();
        }
        return course;
    }

}
