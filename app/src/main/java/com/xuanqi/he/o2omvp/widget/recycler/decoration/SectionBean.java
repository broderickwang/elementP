package com.xuanqi.he.o2omvp.widget.recycler.decoration;

/**
 * Created by sadhu on 2016/9/27.
 * Email static.sadhu@gmail.com
 */
public class SectionBean {

    private boolean isHeader;
    private boolean isGroupStart;
    private boolean isGroupEnd;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isGroupStart() {
        return isGroupStart;
    }

    public void setGroupStart(boolean groupStart) {
        isGroupStart = groupStart;
    }

    public boolean isGroupEnd() {
        return isGroupEnd;
    }

    public void setGroupEnd(boolean groupEnd) {
        isGroupEnd = groupEnd;
    }
}
