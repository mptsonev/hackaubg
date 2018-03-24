package hackatonkm.response;

import java.util.List;

import models.Classroom;

public class GetClassroomsResponse implements GeneralResponse {
    private List<Classroom> classrooms;

    public GetClassroomsResponse(List<Classroom> classrooms) {
        super();
        this.classrooms = classrooms;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
