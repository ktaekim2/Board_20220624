package com.its.board.entity;

import com.its.board.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity { //시간다루는 엔티티 상속받음
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    //회원(1)-게시글(n) 연관관계
    // delete 관련 옵션 없는 경우
//    @OneToMany(mappedBy = "memberEntity")
//    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // on delete cascade(부모데이터를 지우면 자식 데이터도 같이 지워짐)
//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // on delete set null (회원 아이디만 탈퇴시키고, 게시글은 남음, 게시글의 회원 아이디부분이 null 이뜨게)
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.PERSIST, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    //
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    // set null 로 지정 시 삭제 전에 member_id 컬럼을 null로
    @PreRemove
    private void preRemove() {
        boardEntityList.forEach(board -> board.setMemberEntity(null));
//        commentEntityList.forEach(comment -> comment.setMemberEntity(null));
//        for (BoardEntity board : boardEntityList) {
//            board.setMemberEntity(null);
//        }
    }

    public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
