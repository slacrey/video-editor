package com.parsechina.video.engine.group;

import com.parsechina.video.engine.BaseGroupEditor;
import com.parsechina.video.engine.EditorReport;
import sun.jvm.hotspot.ui.Editor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linfeng
 * @date 2019/1/24
 **/
public class SequenceGroupEditor extends BaseGroupEditor {

    private List<Editor> works = new ArrayList<>();

    @Override
    public String name() {
        return "seguence";
    }

    @Override
    public EditorReport call() throws Exception {


        return EditorReport.Default.ofError();
    }


}
