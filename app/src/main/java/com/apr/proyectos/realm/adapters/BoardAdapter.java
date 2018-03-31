package com.apr.proyectos.realm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apr.proyectos.realm.R;
import com.apr.proyectos.realm.models.Board;

import java.util.List;

/**
 * Created by angel on 24/03/2018.
 */

public class BoardAdapter extends BaseAdapter   {

    private Context context;
    private List<Board> list;
    private int layout;

    public BoardAdapter(Context context, List<Board> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Board getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewInterface viewInterface;

        if(view == null)    {
            view = LayoutInflater.from(context).inflate(layout, null);
            viewInterface = new ViewInterface();

            viewInterface.title = (TextView) view.findViewById(R.id.textViewBoardTitle);
            viewInterface.createdAt = (TextView) view.findViewById(R.id.textViewBoardDate);

            view.setTag(viewInterface);
        }   else    {
            viewInterface = (ViewInterface) view.getTag();
        }

        Board board = list.get(i);
        viewInterface.title.setText(board.getTitle());
        viewInterface.createdAt.setText(board.getCreatedAt().toString());

        return view;
    }

    public class ViewInterface  {
        TextView title;
        TextView createdAt;
    }
}
