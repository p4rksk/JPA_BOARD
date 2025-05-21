package com.example.board.board;

import com.example.common.SessionUser;

import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardResponse {

  

    @NoArgsConstructor
    @Data
    public static class BoardListDTO {
        private int id;
        private String title;

        public BoardListDTO(int id, String title) {
            this.id = id;
            this.title = title;
        }

       
    } 

    @NoArgsConstructor
    @Data
    public static class BoardDetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String userName;
        private Boolean isOwner;

        public BoardDetailDTO(Board board, SessionUser sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.userName = board.getUser().getUserName();
            this.isOwner = false;

             if(sessionUser != null){
                if(sessionUser.getId() == userId) isOwner = true;
            }

           
        }

        
    } 

    @NoArgsConstructor
    @Data
     public static class BoardSaveDTO {
        private Integer id;
        private String title;
        private String content;
        
        public BoardSaveDTO(Integer id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
        
    }

    @NoArgsConstructor
    @Data
     public static class BoardUpdateDTO {
        private Integer id;
        private String title;
        private String content;

        public BoardUpdateDTO(Integer id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
        
    }
      
    
}
