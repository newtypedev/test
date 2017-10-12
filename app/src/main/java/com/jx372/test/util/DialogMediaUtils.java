package com.jx372.test.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jx372.test.R;
import com.jx372.test.workapproval.ApprovalActivity;


public class DialogMediaUtils {

	private ApprovalActivity mDialogMediaActivity;
	private Dialog mDialog;
	private String comment="";
	private EditText mDialogComment;
	private TextView mOKButton;
	private TextView mCancelButton;
	private ImageView mDialogImage;
	private TextView mName;
	private EditText mComment;
	private RatingBar mRatingBar;
	private Context context;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public DialogMediaUtils(ApprovalActivity mDialogMediaActivity) {
		this.mDialogMediaActivity = mDialogMediaActivity;
	}

	private void setUpEditor() {

		//editor.dividerBackground=R.drawable.divider_background_dark;

	}

	public void showDialog(Context context) {
		this.context = context;
		if (mDialog == null) {
			mDialog = new Dialog(mDialogMediaActivity,
					R.style.CustomDialogTheme);
		}
		mDialog.setContentView(R.layout.dialog_comment);
		mDialog.getWindow().setGravity(Gravity.BOTTOM);
		mDialog.show();

		mDialogComment = (EditText) mDialog.findViewById(R.id.dialog_media_comment);



		mOKButton = (TextView) mDialog.findViewById(R.id.dialog_media_ok);
		mCancelButton = (TextView) mDialog.findViewById(R.id.dialog_media_cancel);
		mName = (TextView) mDialog.findViewById(R.id.dialog_media_ok);
		mComment = (EditText) mDialog.findViewById(R.id.dialog_media_comment);
	//	mDialogImage = (ImageView) mDialog.findViewById(R.id.dialog_media_image);

//		ImageUtil.displayRoundImage(mDialogImage, "http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg", null);
		

		
		initDialogButtons();
	}

	private void initDialogButtons() {

		mOKButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {


				//Toast.makeText(mDialogMediaActivity, mDialogComment.getText()+"",Toast.LENGTH_SHORT).show();
				//setComment(mDialogComment.getText()+"");
				((ApprovalActivity)context).callbackComment(mDialogComment.getText()+"");
				mDialog.dismiss();
			}
		});

		mCancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				((ApprovalActivity)context).callbackComment("취소");
				mDialog.dismiss();
			}
		});
	}

	public void dismissDialog() {
		mDialog.dismiss();
	}
}
