package yaoyaoandus.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenovo on 2016/8/6.
 */
public class LoginActivity extends Activity{
       TextView textFetchPassWord=null,textRegister=null;//找回密码、注册
       Button loginButton=null;//登陆
       ImageButton listIndicatorButton=null,deleteButtonOfEdit=null;//list的下拉框、list的删除框
       ImageView currentUserImage=null;//当前用户头像
       ListView loginList=null;//登陆账号列表
       EditText login_num_edit=null,login_password_edit=null;
       private static boolean isVisible=false;     //ListView是否可见
       private static boolean isIndicatorUp=false;  //指示器是否向上

       public static int currentSelectedPosition=-1;   //当前List中账号数目，没有用-1表示
       String[] from={"userPhoto","user_login_num","deleteButton"};
       int[] to={R.id.login_userPhoto,R.id.login_user,R.id.login_deleteButton};
       ArrayList<HashMap<String,Object>> list=null;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_layout);
        textFetchPassWord=(TextView)findViewById(R.id.fetchPassword);
        textRegister=(TextView)findViewById(R.id.regist);
        listIndicatorButton=(ImageButton)findViewById(R.id.loginListIndicator);
        loginList=(ListView)findViewById(R.id.loginList);
        list=new ArrayList<HashMap<String, Object>>();
        currentUserImage=(ImageView)findViewById(R.id.myImage);
        login_num_edit=(EditText)findViewById(R.id.loginNum);
        login_password_edit=(EditText)findViewById(R.id.loginPassword);
        deleteButtonOfEdit=(ImageButton)findViewById(R.id.delete_button_edit);

//        输入账号监听
        login_num_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_num_edit.getText().toString().equals("")==false)
                {
                    deleteButtonOfEdit.setVisibility(View.VISIBLE);//假设不为空，就让删除可见
                }
            }
        });
        //假设点了删除
           deleteButtonOfEdit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  currentUserImage.setImageResource(R.drawable.usermain);//这张图片应该是空吧
                   login_num_edit.setText("");//把账号置空
                   currentSelectedPosition=-1;
                   deleteButtonOfEdit.setVisibility(View.GONE);
               }
           });

      //添加用户,涉及到UserInfo类,感觉这个类还是不对诶，还是说我们那个不对

//  UserInfo user1=new UserInfo(R.drawable.contact_0,"1234567",R.drawable.deletebutton);
//  UserInfo user2=new UserInfo(R.drawable.contact_1,"10023455",R.drawable.deletebutton);
//        addUser(user1);
//        addUser(user2);


        if(currentSelectedPosition==-1){
            currentUserImage.setImageResource(R.drawable.usermain);//usermain应该是空白图片
            login_num_edit.setText("");
        }
        else {

            currentUserImage.setImageResource((Integer)list.get(currentSelectedPosition).get(from[0]));//获取头像
            login_num_edit.setText((String)list.get(currentSelectedPosition).get(from[1]));//获取账号
        }
        //这儿登陆，有个MyLoginListAdapter类

          MyLoginListAdapter adapter=new MyLoginListAdapter(this,list,R.layout.login_list_item_layout,from,to);
          loginList.setAdapter(adapter);
          loginList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
               public void onItemClick(AdapterView<?>arg0,View arg1,int arg2,long arg3)
               {
                currentUserImage.setImageResource((Integer)list.get(currentSelectedPosition).get(from[0]));//获取头像
                 login_num_edit.setText((String)list.get(currentSelectedPosition).get(from[1]));//获取账号
                  currentSelectedPosition=arg2;

                loginList.setVisibility(View.GONE);
                listIndicatorButton.setBackgroundResource(R.drawable.indicator_down);

                System.out.print("---------------Selected!!");
               }
          }
          );


        listIndicatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isIndicatorUp){
                    isIndicatorUp=false;
                    isVisible=false;
                    listIndicatorButton.setBackgroundResource(R.drawable.indicator_down);
                    loginList.setVisibility(View.GONE);//让ListView列表消失
                }
                else {
                    isIndicatorUp=true;
                    isVisible=true;
                    listIndicatorButton.setImageResource(R.drawable.indicator_up);//应该存的向上的箭头
                    loginList.setVisibility(View.VISIBLE);
                }
            }
        });




        //暂时把loginbutton啥都不判断，直接跳到mainactivity
        loginButton=(Button)findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });



    }



    //继承onTouchEvent方法，用于实现点击控件之外的部分使控件消失的功能

    private void addUser(yaoyaoandus.contacts.UserInfo user){//不太一样，看来UserInfo类得改一改
            HashMap<String,Object> map= new HashMap<>();//后面<String,Object>
            map.put(from[0],user.userPhoto);//userPhoto
            map.put(from[1],user.userNum);//userQQ 这个可以不一样，改为账号
            map.put(from[2],user.deleteButtonRes);//deleteButtonRes

           list.add(map);
    }

    /**
     * 这段报错了，1.MotionEvent.ACTION_DOWN() method call expected
     *            2.getLocationInWindow(location) cannot resolve symbol
     * @param
     * @return
     */

    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction()==MotionEvent.ACTION_UP)
        {
                int[] location=new int[2];

            loginList.getLocationInWindow(location);
            int x=(int)event.getX();
            int y=(int)event.getY();
            if(x<location[0]||x>location[0]+loginList.getWidth()||y<location[1]||y>location[1]+
                    loginList.getHeight())
            {
                isIndicatorUp=false;
                isVisible=false;
                listIndicatorButton.setImageResource(R.drawable.indicator_down);
                loginList.setVisibility(View.GONE);
            }
        }
        return true;
    }

    public class MyLoginListAdapter extends BaseAdapter{
        protected Context context;
        protected ArrayList<HashMap<String,Object>> list;
        protected int itemLayout;
        protected String[] from;
        protected int[] to;

        public MyLoginListAdapter(Context context,ArrayList<HashMap<String,Object>> list,int itemLayout,
                                  String[] from,int[] to)
        {
            super();
            this.context=context;
            this.from=from;
            this.itemLayout=itemLayout;
            this.list=list;
            this.to=to;
        }

        public int getCount(){
            return list.size();
        }

        public Object getItem(int srg0){
            return null;
        }
        public long getItemId(int position)
        {
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder holder=null;// 这个类还没找到
            ImageView userPhoto=null;
            TextView userNum=null;
            ImageButton deleteButton=null;
            if (convertView==null){
                convertView= LayoutInflater.from(context).inflate(itemLayout,null);
                holder=new ViewHolder(userPhoto,userNum,deleteButton);
                holder.userPhoto=(ImageView)convertView.findViewById(to[0]);
                holder.userNum=(TextView)convertView.findViewById(to[1]);
                holder.deleteButton=(ImageButton)convertView.findViewById(to[2]);
                convertView.setTag(holder);
            }
            else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.userPhoto.setBackgroundResource((Integer)list.get(position).get(from[0]));
            holder.userNum.setText((String)list.get(position).get(from[1]));
            holder.deleteButton.setBackgroundResource((Integer)list.get(position).get(from[2]));
            holder.deleteButton.setOnClickListener(new ListOnClickListener(position));
            return convertView;
        }
        class ListOnClickListener implements View.OnClickListener{
            private int position;
            public ListOnClickListener(int position){
                super();;
                this.position=position;
            }
            public void onClick(View arg0){
                list.remove(position);
                if (position==currentSelectedPosition){
                    currentUserImage.setImageResource(R.drawable.usermain);
                    login_num_edit.setText("");
                    currentSelectedPosition=-1;
                }
                else if(position<currentSelectedPosition){
                    currentSelectedPosition--;
                }
                listIndicatorButton.setImageResource(R.drawable.indicator_down);
                loginList.setVisibility(View.GONE);

                MyLoginListAdapter.this.notifyDataSetChanged();
            }
        }
    }

}
