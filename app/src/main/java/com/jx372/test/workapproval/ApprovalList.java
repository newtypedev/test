package com.jx372.test.workapproval;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 10. 3..
 */

public class ApprovalList {

    private static ApprovalList approvalList;
    private List<Approval> approvals;


    public static ApprovalList get(Context context){
        if(approvalList == null){
            approvalList = new ApprovalList(context);
        }
        return approvalList;
    }

    public void cleanList(){

        approvals.clear();
    }

    private ApprovalList(Context context){
        approvals = new ArrayList<>();
        for(int i=0;i<4;i++){
            Approval approval = new Approval();
            approval.setTitle("업무보고"+i);
            approvals.add(approval);
        }
    }
    public List<Approval> getApprovals(){
        return approvals;
    }



    public Approval getApprovals(UUID id){
        for(Approval approval: approvals){
            if(approval.getId().equals(id)){
                return approval;
            }
        }
        return null;
    }





}
