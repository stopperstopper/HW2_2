package data;

public class TrainingCoursesContainerData {
    private String name;
    private Integer price;

    public TrainingCoursesContainerData(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TrainingCourseBlockEntity{" +
                "Название='" + name + '\'' +
                ", цена='" + price + '\'' +
                '}';
    }
}
