package com.jx372.test.weekplansearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jx372.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by anandbose on 09/06/15.
 */
public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;
    private LinearLayout childId;
    private List<Item> data;


    public ExpandableListAdapter(List<Item> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {

        View view = null;
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int subItemPaddingLeft = (int) (18 * dp);
        int subItemPaddingTopAndBottom = (int) (5 * dp);
        switch (type) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_week_header_view, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;
            case CHILD:
                LayoutInflater inflater2 = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater2.inflate(R.layout.list_week_child_view, parent, false);
                ListChildViewHolder child = new ListChildViewHolder(view);
                return child;
//                itemTextView.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);
//                itemTextView.setTextColor(0x88000000);
//                itemTextView.setLayoutParams(
//                        new ViewGroup.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));

//               return new RecyclerView.ViewHolder(view) {
//                };
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.weekachieve.setText(item.weekachieve);
                itemController.weeksale.setText(item.weeksale);
                itemController.weektarget.setText(item.weektarget);
                itemController.header_title.setText(item.text);
                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.circle_minus);
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.circle_plus);
                }
                itemController.btn_expand_toggle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);
                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.circle_plus);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.circle_minus);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:
                final ListChildViewHolder childitem = (ListChildViewHolder) holder;
              childitem.daytext.setText(data.get(position).daytext);
                childitem.daycontent.setText(data.get(position).daycontent);
                childitem.daytarget.setText(data.get(position).daytarget);
//                TextView itemTextView = (TextView) holder.itemView;
  //              itemTextView.setText(data.get(position).text);


                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public TextView weekachieve;
        public TextView weektarget;
        public TextView weeksale;
        public ImageView btn_expand_toggle;
        public Item refferalItem;
        public TextView daytext;

        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            weeksale = (TextView)itemView.findViewById(R.id.weeksale);
            weektarget = (TextView)itemView.findViewById(R.id.weektarget);
            header_title = (TextView) itemView.findViewById(R.id.header_title);
            btn_expand_toggle = (ImageView) itemView.findViewById(R.id.btn_expand_toggle);
            weekachieve = (TextView) itemView.findViewById(R.id.weekachieve);
        //    daytext = (TextView)itemView.findViewById(R.id.daytext);
        }


    }
    private static class ListChildViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public TextView weekachieve;
        public ImageView btn_expand_toggle;
        public Item refferalItem;
        public TextView daytext;
        public TextView daycontent;
        public TextView daytarget;

        public ListChildViewHolder(View itemView) {
            super(itemView);
                daytext = (TextView)itemView.findViewById(R.id.daytext);
            daytarget = (TextView)itemView.findViewById(R.id.daytarget);
            daycontent = (TextView)itemView.findViewById(R.id.daycontent);
        }


    }

    public static class Item {
        public int type;
        public String text;
        public List<Item> invisibleChildren;
        public String daytext;
        public String weektarget;
        public String weeksale;
        public String weekachieve;
        public String daytarget;
        public String daycontent;

        public Item() {
        }

//        public Item(int type, String text) {
//            this.type = type;
//            this.text = text;
//        }
public Item(int type, Map map) {
    if(type==HEADER){
    this.type = type;
        this.weektarget = map.get("weektarget").toString();
        this.weeksale = map.get("weeksale").toString();
    this.weekachieve = map.get("weekachieve").toString();
    this.text = map.get("userid").toString();}
    else{
        this.type = type;
        this.daytext = map.get("daytext").toString();
        this.daytarget = map.get("daytarget").toString();
        this.daycontent = map.get("daycontent").toString();
}
}
    }
}
