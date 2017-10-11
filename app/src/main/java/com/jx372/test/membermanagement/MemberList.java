package com.jx372.test.membermanagement;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 10. 6..
 */

public class MemberList {

    private static MemberList memberList;
    private List<Member> members;


    public static MemberList get(Context context){
        if(memberList == null){
            memberList = new MemberList(context);
        }
        return memberList;
    }

    public void cleanList(){

        members.clear();
    }

    private MemberList(Context context){
        members = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            Member member = new Member();
//            member.setTitle("멤버"+i);
//            members.add(member);
//        }
    }
    public List<Member> getMembers(){
        return members;
    }
    public void addMember(Member m){

        // consult.setTitle(s);
        // consult.setSolved(b);
        members.add(m);
    }


    public Member getMembers(UUID id){
        for(Member member: members){
            if(member.getId().equals(id)){
                return member;
            }
        }
        return null;
    }



}
