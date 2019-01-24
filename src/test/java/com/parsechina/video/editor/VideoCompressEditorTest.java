package com.parsechina.video.editor;

import com.parsechina.video.editor.compress.VideoCompressEditor;
import com.parsechina.video.editor.watermask.VideoWatermaskEditor;
import org.junit.Test;

/**
 * @author linfeng-eqxiu
 * @description //TODO 设计说明
 * @date 2019/1/24
 */
public class VideoCompressEditorTest {

    @Test
    public void test() {

        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setFilePath("D:\\test\\record\\mayun.mp4");

        Parameter parameter = new Parameter();
        parameter.put(mediaInfo);

        VideoCompressEditor editor = new VideoCompressEditor();
        editor.params(parameter);
        try {
            EditorReport<MediaInfo> report = editor.call();
            if (report.getStatus().isOk()) {

                VideoWatermaskEditor watermaskEditor = new VideoWatermaskEditor();
                parameter.put(report.getData());
                watermaskEditor.params(parameter);

                EditorReport<MediaInfo> watermaskReport = watermaskEditor.call();
                if (watermaskReport.getStatus().isOk()) {
                    System.out.println(watermaskReport.getData().getFilePath());
                } else {
                    System.out.println(watermaskReport.getError());
                }

            } else {
                System.out.println(report.getError());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}