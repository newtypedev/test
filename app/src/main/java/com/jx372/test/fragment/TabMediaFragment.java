package com.jx372.test.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.MileageActivity;
import com.jx372.test.MileageItem;
import com.jx372.test.R;
import com.jx372.test.User;

import java.io.InputStream;
import java.net.URL;

public class TabMediaFragment extends Fragment implements OnClickListener {

	private static final String ARG_POSITION = "position";
	private ImageView mImage;
	private TextView mLike;
	private TextView mFavorite;
	private TextView mShare;
	private Button btnStart;
	private Button btnEnd;
	private TextView textStart;
	private TextView textEnd;
	private MileageItem mileageItem;

	private int position;
	class DownThread extends Thread {
		String mAddr;

		DownThread(String addr) {
			mAddr = addr;
		}

		public void run() {
			try {
				InputStream is = new URL(mAddr).openStream();
				Bitmap bit = BitmapFactory.decodeStream(is);
				is.close();
				Message message = mAfterDown.obtainMessage();
				message.obj = bit;
				mAfterDown.sendMessage(message);
			} catch (Exception e) {;}
		}
	}
	Handler mAfterDown = new Handler() {
		public void handleMessage(Message msg) {
			Bitmap bit = (Bitmap)msg.obj;
			if (bit == null) {
				Toast.makeText(getActivity(), "이미지가 없습니다", Toast.LENGTH_LONG).show();
			} else {
				mImage.setImageBitmap(bit);
			}
		}
	};

	public void startMileageActivity(){
		Intent i = new Intent(getActivity(),MileageActivity.class);
		//  i.putExtra("day",msg);
		startActivity(i);

	}

	public static TabMediaFragment newInstance(int position) {
		TabMediaFragment f = new TabMediaFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_mileage,
				container, false);

		mileageItem = MileageItem.get();
		btnStart = (Button)rootView.findViewById(R.id.btnstartmile);
		btnEnd = (Button)rootView.findViewById(R.id.btnendmile);
		textStart = (TextView) rootView.findViewById(R.id.startmile);
		textEnd =  (TextView) rootView.findViewById(R.id.endmile);
		textStart.setOnClickListener(new TextView.OnClickListener(){
			@Override
			public void onClick(View v) {



				Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.dialog_imageview);


				mImage = (ImageView) dialog.findViewById(R.id.image);
				(new DownThread("http://www.hankyung.com/pdsdata/bbs3/choi_ki_sung/f711f4123842792aa36a1b9fa79d8b96")).start();


				dialog.show();

//                final LinearLayout linear = (LinearLayout)
//                        View.inflate(getActivity(), R.layout.dialog_weeksale, null);
//
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("목표액 수정")
//                        .setView(linear)
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//
//                            }
//                        })
//                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        })
//                        .show();

			}
		});
textStart.setText(User.get().getStart()+"");
		textEnd.setOnClickListener(new TextView.OnClickListener(){
			@Override
			public void onClick(View v) {



				Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.dialog_imageview);


				mImage = (ImageView) dialog.findViewById(R.id.image);
				(new DownThread("http://www.soen.kr/data/0.jpg")).start();


				dialog.show();

//                final LinearLayout linear = (LinearLayout)
//                        View.inflate(getActivity(), R.layout.dialog_weeksale, null);
//
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("목표액 수정")
//                        .setView(linear)
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//
//                            }
//                        })
//                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        })
//                        .show();

			}
		});


		btnStart.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {

				if(mileageItem.getStartdis().equals("0")) {

					Intent i = MileageActivity.newIntent(getActivity(), true);
					startActivity(i);
				}
				else {
					Toast.makeText(getActivity(),"이미 등록하셨습니다",Toast.LENGTH_LONG);
				}
			}});

		btnEnd.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {

				if(mileageItem.getEnddis().equals("0")) {

					Intent i = MileageActivity.newIntent(getActivity(),false);
					startActivity(i);

				}
				else {
					Toast.makeText(getActivity(),"이미 등록하셨습니다",Toast.LENGTH_LONG);
				}

			}});




		return rootView;
	}

	@Override
	public void onClick(View v) {

	}
}