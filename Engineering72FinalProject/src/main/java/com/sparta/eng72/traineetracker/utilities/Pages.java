package com.sparta.eng72.traineetracker.utilities;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class Pages {

    //LOGIN =========================================================================================
    public static final String LOGIN_PAGE = "/login/login";
    public static final String LOGIN_PAGE_URL = "/login";
    public static final String LOGIN_FAILURE_URL = "/login";
    public static final String USER_NOT_FOUND_PAGE = "/login/userNotFound";
    public static final String FIRST_PASSWORD_PAGE = "/first-time-user/firstTimeLogin";
    public static final String FIRST_PASSWORD_URL = "trainee/firstPassword";
    public static final String LOGOUT_CURRENT_USER = "/login/login";
    public static final String CHANGE_PASSWORD_PAGE = "/login/passwordChanger";
    public static final String RECOVER_PASSWORD_PAGE = "/login/recoverPassword";
    public static final String PASSWORD_SENT_PAGE = "/login/passwordSent";
    public static final String TRAINEE_CHANGE_PASSWORD_URL = "/trainee/changePassword";
    public static final String TRAINER_CHANGE_PASSWORD_URL = "/trainer/changePassword";
    public static final String RECOVER_PASSWORD_URL = "/login/recoverPassword";

    //TRAINEE =======================================================================================
    public static final String TRAINEE_HOME_PAGE = "/trainee/traineeHome";
    public static final String TRAINEE_HOME_URL = "/trainee/home";
    public static final String TRAINEE_GUIDE_PAGE = "/traineeGuide";
    public static final String TRAINEE_GUIDE_URL = "/trainee/guide";
    public static final String TRAINEE_REPORT_PAGE = "/trainee/traineeReport";
    public static final String TRAINEE_REPORT_URL = "/trainee/report";
    public static final String TRAINEE_FEEDBACK_FORM_PAGE = "/trainee/traineeForm";
    public static final String TRAINEE_ASSESSMENTS = "/trainee/traineeAssessment";
    public static final String TRAINEE_PROFILE_PAGE = "/trainee/traineeProfile";


    //TRAINER =======================================================================================
    public static final String TRAINER_HOME_PAGE = "/trainer/trainerHome";
    public static final String TRAINER_HOME_URL = "/trainer/home";
    public static final String TRAINER_NEW_USER_PAGE = "/trainer/newUserForm";
    public static final String TRAINER_NEW_USER_URL = "/trainer/addTrainee";
    public static final String TRAINER_NEW_USER_ALREADY_EXISTS_PAGE = "/trainer/newUserAlreadyExists";
    public static final String TRAINER_NEW_USER_ALREADY_EXISTS_URL = "/trainer/newUserAlreadyExists";
    public static final String TRAINER_NEW_GROUP_ALREADY_EXISTS_PAGE = "/trainer/newGroupAlreadyExists";
    public static final String TRAINER_NEW_GROUP_ALREADY_EXISTS_URL = "/trainer/newGroupAlreadyExists";
    public static final String TRAINER_FEEDBACK_FORM_PAGE = "/trainer/trainerFeedbackForm";
    public static final String TRAINER_REPORT_PAGE = "/trainer/trainerReport";
    public static final String TRAINER_REPORT_URL = "/trainer/report";
    public static final String TRAINER_GROUPS_PAGE = "/trainer/groups";
    public static final String TRAINER_GROUPS_URL = "/trainer/group";
    public static final String TRAINER_GROUPS_SUBMIT_PAGE = "/trainer/groupsSubmit";
    public static final String TRAINER_GROUPS_SUBMIT_URL = "/trainer/groupsSubmit";
    public static final String TRAINER_ADD_WEEK_PAGE = "/trainer/newWeekPage";
    public static final String TRAINER_ADD_WEEK_URL = "/trainer/addNewWeek";
    public static final String TRAINER_WEEK_SUCCESS_PAGE ="/trainer/weekSuccess";
    public static final String TRAINER_WEEK_SUCCESS_URL ="/trainer/weekSuccess";
    public static final String TRAINER_TRAINEE_ASSESSMENTS = "/trainer/traineeAssessment";
    public static final String TRAINER_ATTENDANCE= "/trainer/traineeAttendance";
    public static final String TRAINEE_ATTENDANCE = "/trainee/traineeAttendance";
    public static final String TRAINER_NEW_USER_SUCCESS ="/trainer/userSubmit";
    public static final String TRAINER_DELETE_SUCCESS ="/trainer/deleteSubmit";
    public static final String TRAINER_ASSESSMENTS ="/trainer/trainerAssessment";
    public static final String TRAINER_ATTENDANCE_PAGE ="/trainer/attendance";
    public static final String TRAINER_ATTENDANCE_SUCCESS ="/trainer/attendanceSuccess";
    public static final String TRAINER_WEEKLY_ATTENDANCE ="/trainer/weeklyAttendance";



    //Any =======================================================================================
    public static final String CONSULTANCY = "/consultancy";
    public static final String ATTENDANCE_PERCENTAGES = "/fragments/attendancePercentages";


    //ERROR =========================================================================================
    public static final String ACCESS_ERROR = "/errors/accessError";
    public static final String PAGE_NOT_FOUND_ERROR = "/errors/pagenotfounderror";
    public static final String NO_ITEM_IN_DATABASE_ERROR = "/errors/pagenotfounderror";
    public static String accessPage(String requiredRole, String page) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (request.isUserInRole(requiredRole) || requiredRole.equals(Role.ANY)) {
            return page;
        } else {
            return ACCESS_ERROR;
        }
    }

}
