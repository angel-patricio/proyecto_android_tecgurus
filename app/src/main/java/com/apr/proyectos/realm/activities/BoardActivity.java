package com.apr.proyectos.realm.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apr.proyectos.realm.R;
import com.apr.proyectos.realm.adapters.BoardAdapter;
import com.apr.proyectos.realm.models.Board;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class BoardActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>>    {

    private Realm realm;
    private RealmResults<Board> boards;

    private ListView listViewBoard;
    private BoardAdapter adapterBoard;

    private FloatingActionButton fabButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        // Conexión a la bd de realm
        realm = Realm.getDefaultInstance();
        boards = realm.where(Board.class).findAll();
        boards.addChangeListener(this);

        adapterBoard = new BoardAdapter(this, boards, R.layout.list_view_board_item);
        listViewBoard = (ListView) findViewById(R.id.listViewBoard);
        listViewBoard.setAdapter(adapterBoard);

        fabButtonAdd = (FloatingActionButton) findViewById(R.id.fabAddBoard);
        fabButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddBoard("Agregar nuevo tablero", "Ingresa el nombre del tablero");
            }
        });

    }

    /**
     * CRUD ACTIONS
     */
    private void createNewBoard(String boardName)   {
        realm.beginTransaction();
        Board board = new Board(boardName);
        realm.copyToRealm(board);
        realm.commitTransaction();
    }

    public void showAddBoard(String title, String message)  {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewBoard);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = input.getText().toString().trim();
                if(boardName.length() > 0)  {
                    createNewBoard(boardName);
                }   else    {
                    Toast.makeText(getApplicationContext(), "Se requiere un nombre para el tablero", Toast.LENGTH_LONG).show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onChange(RealmResults<Board> boards) {
        adapterBoard.notifyDataSetChanged();
    }
}
