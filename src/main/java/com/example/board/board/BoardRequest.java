package com.example.board.board;

import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardRequest {

    @NoArgsConstructor
    @Data
    public static class  SaveDTO {
        private String title;
        private String content;

        
        public SaveDTO(String title, String content) {
            this.title = title;
            this.content = content;
        }

        
    }    

    @NoArgsConstructor
    @Data
    public static class  UpdateDTO {
        private String title;
        private String content;

        
        public UpdateDTO(String title, String content) {
            this.title = title;
            this.content = content;
        }

        
    }    

}
