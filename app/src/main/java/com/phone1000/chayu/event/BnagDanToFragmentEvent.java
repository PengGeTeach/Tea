package com.phone1000.chayu.event;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class BnagDanToFragmentEvent {

    public final int WHAT;
    private String leibie;
    private String nianfen;
    private String bangming;

    public String getNianfen() {
        return nianfen;
    }

    public void setNianfen(String nianfen) {
        this.nianfen = nianfen;
    }

    public String getBangming() {

        return bangming;
    }

    public void setBangming(String bangming) {
        this.bangming = bangming;
    }

    public String getLeibie() {
        return leibie;
    }

    public void setLeibie(String leibie) {
        this.leibie = leibie;
    }

    public BnagDanToFragmentEvent(int what) {
        WHAT = what;
    }
}
