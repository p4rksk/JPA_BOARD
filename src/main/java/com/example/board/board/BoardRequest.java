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

}
