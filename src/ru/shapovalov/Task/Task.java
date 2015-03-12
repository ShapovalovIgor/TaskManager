package ru.shapovalov.Task;

import java.util.Date;


public class Task  {
    private String name;
    private String description;
    private Date startDate;
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;



    public Task() {}
   public Task(String name, String description, Date startDate, boolean active, Integer id){
      this.name = name;
      this.description = description;
      this.startDate = startDate;
      this.active = active;
       this.id = id;
   }
    public String getName() { return this.name; }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getDescription() { return this.description; }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getStartDate() { return this.startDate; }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public boolean getActive() { return this.active; }

    public void setActive(boolean active)
    {
        this.active = active;
    }



}
