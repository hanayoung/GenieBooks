package com.ssafy.cafe.model.dto;

import java.util.Date;

public class Attendance {
    private int id;
    private int cId;
    private Date attendDate;

    public Attendance() {}
    public Attendance(int id, int cId, Date attendDate) {
        this.id = id;
        this.cId = cId;
        this.attendDate = attendDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public Date getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Date attendDate) {
        this.attendDate = attendDate;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", cId=" + cId +
                ", attendDate=" + attendDate +
                '}';
    }
}
