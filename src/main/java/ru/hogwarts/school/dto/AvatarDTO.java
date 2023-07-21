package ru.hogwarts.school.dto;

import java.util.Objects;

public class AvatarDTO {

    private final String filePath;
    private final long fileSize;
    private final String mediaType;
    private final long studentId;

    public AvatarDTO(String filePath, long fileSize, String mediaType, long studentId) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.studentId = studentId;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public long getStudentId() {
        return studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarDTO avatarDTO = (AvatarDTO) o;
        return fileSize == avatarDTO.fileSize && studentId == avatarDTO.studentId && Objects.equals(filePath, avatarDTO.filePath) && Objects.equals(mediaType, avatarDTO.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath, fileSize, mediaType, studentId);
    }

    @Override
    public String toString() {
        return "AvatarDTO{" +
                "filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
