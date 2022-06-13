package data;

import java.time.LocalDate;

public class CoursesContainerData {
  private String name;
  private LocalDate startDate;

  public CoursesContainerData(String name, LocalDate startDate) {
    this.name = name;
    this.startDate = startDate;
  }

  @Override
  public String toString() {
    return "CourseBlockEntity{"
      +"Название='" + name + '\''
      +", дата старта курса=" + startDate
      +'}';
  }

  public String getName() {
    return name;
  }

  public LocalDate getStartDate() {
    return startDate;
  }
}
