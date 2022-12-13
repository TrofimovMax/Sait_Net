package com.example.Inst_Sait.services;

import com.example.Inst_Sait.entity.Video;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface VideoService {
    Video getVideo(String name);

    void saveVideo(MultipartFile file, String name) throws IOException;

    List<String> getAllVideoNames();
}
