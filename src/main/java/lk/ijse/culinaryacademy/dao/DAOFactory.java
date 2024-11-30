package lk.ijse.culinaryacademy.dao;

import lk.ijse.culinaryacademy.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER, COURSE,PAYMENT,STUDENT,STUDENTCOURSEDETAILS
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case STUDENTCOURSEDETAILS:
                return new StudentCourseDetailDAOImpl();
            default:
                return null;
        }
    }

}
