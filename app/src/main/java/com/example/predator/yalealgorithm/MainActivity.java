package com.example.predator.yalealgorithm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.predator.yalealgorithm.R;

import java.util.Random;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    private int SMatrix[][], A[], IA[], JA[];
    private EditText Rows,Columns,NNZTV;
    private TextView outputTV, aTV, iaTV, jaTV;


    private int count=0;
    int IACount=0;

    private Random r= new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Rows = (EditText)  findViewById(R.id.Rows);
         Columns = (EditText)  findViewById(R.id.Columns);
         outputTV = (TextView) findViewById(R.id.outputTV);
         aTV = (TextView) findViewById(R.id.aTV);
         iaTV = (TextView) findViewById(R.id.iaTV);
         jaTV = (TextView) findViewById(R.id.jaTV);
        NNZTV=(EditText)findViewById(R.id.Nzero);

    }

    //3 Array Implementation of Yale Algorithm
    //Array A, Displays non-Zero Elements
    private void A_Array()
    {
        String output = "";
        for(int i = 0; i <  SMatrix.length; i++)
        {
            for (int j = 0; j <  SMatrix[i].length; j++)
            {
                if( SMatrix[i][j] != 0)
                {
                    output +=  SMatrix[i][j] + " ";
                }
            }
        }
        String[] MEle = output.trim().split(" ");
         A = new int[MEle.length];
        for(int i = 0; i <  A.length; i++)
        {
             A[i] = Integer.parseInt(MEle[i]);
        }
    }

    //Array IA, Splits the array into rows
    private void IA_Array()
    {
         IA = new int[ SMatrix.length+1];
        int runningTotal = 0;
        int IACount=0;
        for(int i = 0; i <  IA.length-1; i++)
        {
             IA[i] = runningTotal;
            for(int j = 0; j <  SMatrix[i].length; j++)
            {
                if( SMatrix[i][j] != 0)
                {
                    runningTotal++;
                    IACount++;
                }
            }
        }
         IA[ IA.length-1] = runningTotal;
    }

    //Array JA, Aligns the values in columns
    private void JA_Array()
    {
        int currPosInA = 0;
        JA = new int[ A.length];
        int numInThisRow;

        for(int i = 1; i <  IA.length; i++)
        {
            numInThisRow =  IA[i] -  IA[i-1];

            int maxIndex = currPosInA + numInThisRow;

            for(int j = currPosInA; j <  A.length && j < maxIndex; j++)
            {
                JA[j] =  findPosInRow( A[j], i-1);
                currPosInA++;
            }
        }
    }

    private int findPosInRow(int num, int row)
    {
        for(int i = 0; i <  SMatrix[row].length; i++)
        {
            if( SMatrix[row][i] == num)
            {
                return i;
            }
        }
        return -1;
    }


    private String arrayToString(int[] ar)
    {
        String output = " ";
        for(int i = 0; i < ar.length; i++)
        {
            output += ar[i] + " ";
        }
        return output;
    }

    public void clearButtonPressed(View v)
    {
        outputTV.setText("");
    }

    //Activates and displays Yale algorithm functions
    public void generateYaleButtonClicked(View v)
    {
         A_Array();
         IA_Array();
         JA_Array();
         aTV.setText( arrayToString( A));
         iaTV.setText( arrayToString( IA));
         jaTV.setText( arrayToString( JA));
        String output="";

/*
        int row_index = 0;
        int col_index = IA.length - 1;
        int n = A.length;
        int max = JA[0];
        int temp = 0;
        for ( int i=0; i < JA.length-1; i++)
        {
            if(JA[i] < JA[i+1])
            {
                max = JA[i+1];
            }
        }
        int [][] mat = new int [row_index][col_index];
        for (  int i=0;i<col_index;i++)
        {
            for (int j=0;j<row_index;j++)
            {
                mat[j][i] = 0;
            }
        }

        int index_A = 0;
        int index_IA =0;
        for (   index_IA=0;index_IA<IA.length-1;index_IA++)
        {
             temp = IA[index_IA];
            int next_index = IA[index_IA + 1];
            while (next_index > 10)
            {
                mat[JA[index_A]][index_IA] = A[index_A];
                index_A++;
                next_index--;
            }
        }


        String output_mat = "";
        //Displaying the Matrix
        for(int i = 0; i <  SMatrix.length; i++)
        {
            for(int j = 0; j <  SMatrix[i].length; j++)
            {
                output_mat +=   SMatrix[i][j]+"   ";
                outputTV.setText( output);
            }
            output_mat += "\n";

        }
        //SMatrix = new int[row_index][col_index];
*/
       /* if(IA[1]==0&&IA[0]==0)
    {
        SMatrix[0][1]=A[0];
        SMatrix[1][1]=A[1];
        int i=0,j;


         for(i=1;i<IA.length;i++)
            {
               for(j=1;j<IA[i];j++)
                  {
                     output+=A[j];
                     output += "\n";
                     outputTV.setText( output);
                  }
                output += "\n";

     }
    }
    else
    {*/
        int count = 0;
        int IA_index = 1;
        int IA_value = 0;
    for(int i=0; i < IA.length-1 ; i++)
    {
        //IA_index = i + 1;
        IA_value = IA[IA_index];

        for(int j=0;j<JA.length;j++)
        {
            if(j == JA[i] && IA_value > 0)
            {
                output += " " + A[count];
                count++;
                IA_value--;
            }
            else
            {
                output += " 0";
            }
         }
        IA_index++;
        output += "\n";
    }
        outputTV.setText( output);
    }

    //}


    //Generates a matrix of desired rows and columns with 25% chance of non-zero elements
    public void generateMatrixButtonClicked(View v)
    {
        int rows = Integer.parseInt( Rows.getText().toString());
        int cols = Integer.parseInt( Columns.getText().toString());
        int Nonzero=Integer.parseInt(NNZTV.getText().toString());

        count=0;
        if (Nonzero>(rows*cols))
        {
            exit(0);
        }
        SMatrix = new int[rows][cols];
        for(int i = 0; i <  SMatrix.length; i++)
        {
            if(count!=Nonzero)
            {
            for(int j = 0; j <  SMatrix[i].length; j++)
            {
               if(r.nextInt(200) < 175)
                {
                    if(count!=Nonzero)
                    {
                        count++;
                        SMatrix[i][j] = r.nextInt(500) + 1;
                    }
                }
                else
                {
                 SMatrix[i][j] = 0;
                }
            }
            }
        }

        String output = "";
        //Displaying the Matrix
        for(int i = 0; i <  SMatrix.length; i++)
        {
            for(int j = 0; j <  SMatrix[i].length; j++)
            {
                output +=   SMatrix[i][j]+"   ";
            }
            output += "\n";
            outputTV.setText( output);
        }

    }
}
/*
if(IA[1]==0)
{

}
for(int i=0;i<IA.lenght;i++)
{
    for(int j=0;j<IA[i].lenght;j++)
    {
    output+=SMatrix[i][j]+"   ";
     output += "\n";
            outputTV.setText( output);
    }
}
 */