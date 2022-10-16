package com.student.challenge.profiler;

//TODO need cover with tests
public class TimeProfiler {
    private long start;
    private long end;

    public void start() {
       start = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public long durationInMs() {
        return end - start;
    }
}
