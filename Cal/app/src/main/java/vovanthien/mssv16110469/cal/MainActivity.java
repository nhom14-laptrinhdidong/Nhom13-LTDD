package vovanthien.mssv16110469.cal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import  android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bDot,bSquared,bSqrt,bLeft,bRight,bInfo,bAdd,bSub,bMul,bDiv,bOpen,bClose,bDelete,bDeleteAll,bEqual;
    private TextView tInput, tResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventGetID();

        setEventclickView();
    }
//Sự kiện lấy id của từng button và textview
    public void EventGetID() {
        b0= (Button)findViewById(R.id.btn0);
        b1= (Button)findViewById(R.id.btn1);
        b2= (Button)findViewById(R.id.btn2);
        b3= (Button)findViewById(R.id.btn3);
        b4= (Button)findViewById(R.id.btn4);
        b5= (Button)findViewById(R.id.btn5);
        b6= (Button)findViewById(R.id.btn6);
        b7= (Button)findViewById(R.id.btn7);
        b8= (Button)findViewById(R.id.btn8);
        b9= (Button)findViewById(R.id.btn9);
        bDot=(Button)findViewById(R.id.btnDot);
        bSquared= (Button)findViewById(R.id.btnSquared);
        bSqrt= (Button)findViewById(R.id.btnSqrt);
        bLeft= (Button)findViewById(R.id.btnLeft);
        bRight= (Button)findViewById(R.id.btnRight);
        bInfo= (Button)findViewById(R.id.btnInfo);
        bAdd= (Button)findViewById(R.id.btnAdd);
        bSub= (Button)findViewById(R.id.btnSub);
        bMul= (Button)findViewById(R.id.btnMul);
        bDiv= (Button)findViewById(R.id.btnDiv);
        bOpen= (Button)findViewById(R.id.btnOpen);
        bClose= (Button)findViewById(R.id.btnClose);
        bDelete= (Button)findViewById(R.id.btnDelete);
        bDeleteAll= (Button)findViewById(R.id.btnDeleteAll);
        bEqual= (Button)findViewById(R.id.btnEqualsSign);

        tInput=(TextView) findViewById(R.id.txtInput);
        tResult=(TextView)findViewById(R.id.txtResult);


    }
    //Set sự kiện khi click vào các button
    public void setEventclickView()
    {
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bDot.setOnClickListener(this);
        bSquared.setOnClickListener(this);
        bSqrt.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        bRight.setOnClickListener(this);
        bInfo.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        bSub.setOnClickListener(this);
        bMul.setOnClickListener(this);
        bDiv.setOnClickListener(this);
        bOpen.setOnClickListener(this);
        bClose.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bDeleteAll.setOnClickListener(this);
        bEqual.setOnClickListener(this);

    }
    //Khi click vào button nó sẽ chạy phương thức setEventclickView để thực hiện lệnh click tương ứng button đó
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0:
                tInput.append("0");
                break;
            case R.id.btn1:
                tInput.append("1");
                break;
            case R.id.btn2:
                tInput.append("2");
                break;
            case R.id.btn3:
                tInput.append("3");
                break;
            case R.id.btn4:
                tInput.append("4");
                break;
            case R.id.btn5:
                tInput.append("5");
                break;
            case R.id.btn6:
                tInput.append("6");
                break;
            case R.id.btn7:
                tInput.append("7");
                break;
            case R.id.btn8:
                tInput.append("8");
                break;
            case R.id.btn9:
                tInput.append("9");
                break;
            case R.id.btnSquared:
                tInput.append("^");
                break;
            case R.id.btnSqrt:
                tInput.append("√");
                break;
            case R.id.btnLeft:

                break;
            case R.id.btnRight:

                break;
            case  R.id.btnInfo:

                Intent i = new Intent(getApplicationContext(), PopupActivity.class);
                startActivity(i);
                break;
            case R.id.btnAdd:
                tInput.append("+");
                break;
            case R.id.btnSub:
                tInput.append("-");
                break;
            case R.id.btnMul:
                tInput.append("*");
                break;
            case R.id.btnDiv:
                tInput.append("/");
                break;
            case R.id.btnOpen:
                tInput.append("(");
                break;
            case R.id.btnClose:
                tInput.append(")");
                break;
            case R.id.btnDelete:
                String temp= delete(tInput.getText().toString());
                tInput.setText(temp);
                break;
            case R.id.btnDeleteAll:
                tInput.setText("");
                break;
            case  R.id.btnDot:
                tInput.append(".");
                break;
            case R.id.btnEqualsSign:
                DecimalFormat df=new DecimalFormat("########.########");
                String sMath = tInput.getText().toString();
                PostFixConverter pc = new PostFixConverter(sMath);
                PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
                String result = calc.result().toString();
                tResult.setText(result);
                break;
        }
    }
    //Xóa 1 kí tự
    public String delete(String s)
    {
        int length=s.length();
        String temp= s.substring(0,length-1);
        return temp;
    }
    //Chứa các kí tự +,-,*,/,(,)
    public ArrayList<String> arrOperation;
    //Chứa các số
    public  ArrayList<String > arrNumber;
    //Lấy các mảng phép tính lưu vào
    public int arrCalculate(String input)
    {
        arrOperation=new ArrayList<>();
        arrNumber=new ArrayList<>();
        char[] cArr=input.toCharArray();
        for(int i=0;i<cArr.length;i++)
        {
            switch (cArr[i]) {
                case '+':
                    arrOperation.add(cArr[i]+"");
                    break;
                case '-':
                    arrOperation.add(cArr[i]+"");
                    break;
                case '*':
                    arrOperation.add(cArr[i]+"");
                    break;
                case '/':
                    arrOperation.add(cArr[i]+"");
                    break;
                case '^':
                    arrOperation.add(cArr[i]+"");
                    break;
                case '√':
                    arrOperation.add(cArr[i]+"");
                    break;
                case '(':
                    arrOperation.add(cArr[i]+"");
                    break;
                case ')':
                    arrOperation.add(cArr[i]+"");
                    break;
                    default:
                        break;
            }
        }
        return 0;
    }
}
