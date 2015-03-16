package com.example.deepika.click;

/**
 * Created by Deepika on 04-03-2015.
 */
public class Entry {
    private String _name;
    private long _time;
    private int _id = 0;

    Entry(String name, long time) {

        _name = name;
        _time = time;
    }

    Entry() {
        _id=0;
        _name="player1";
        _time=0;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        _id = id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public long get_time() {
        return _time;
    }

    public void set_time(long _time) {
        this._time = _time;
    }


    String printEntry() {
        return get_id()+ " "+ get_name()+ " "+ get_time();
    }
}