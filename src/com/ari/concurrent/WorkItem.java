package com.ari.concurrent;

import java.util.Objects;

public class WorkItem{
    String name;
    long workload;

    public WorkItem(String name, long workload) {
        this.name = name;
        this.workload = workload;
    }

    public long getWorkload() {
        return workload;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkItem)) return false;
        WorkItem workItem = (WorkItem) o;
        return workload == workItem.workload &&
                Objects.equals(name, workItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, workload);
    }
}
