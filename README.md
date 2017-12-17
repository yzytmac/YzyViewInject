# YzyViewInject
手写ButterKnife注解框架  
**使用方法**  

- gradle添加依赖compile 'com.github.yzytmac:yzyinject:2.0.0'    
- @ContentView(R.layout.activity_main)绑定布局  
- @ViewInject(R.id.bt)绑定控件 
- @ViewInject(value = R.id.tv,text="我是文本")绑定控件并设置文本，注意，控件必须是textview的子类
- @Click({R.id.bt,R.id.bt2})绑定点击事件的方法，方法名随意，参数可以为空或一个view 
- 在onCreate方法中InjectUtils.injectActivity(this)对Activity进行注入，相当于框架的初始化，**必须执行**
- 在onCreateView方法中InjectUtils.injectFragment(this)对Fragment进行注入，将返回值return出去，也是**必须执行**，且必须用@ContentView()的形式进行布局绑定  
- 有任何疑问欢迎发邮件到yzytmac@163.com 欢迎提交代码  


	    /***************************Activity*********************************/
	    @ContentView(R.layout.activity_main)//为Activity绑定布局
		public class MainActivity extends AppCompatActivity {
    		@ViewInject(R.id.bt)
    		private Button bt;//为控件绑定id
    		@ViewInjecr(value = R.id.bt2,text = "我是按钮2")
    		private Button bt2;

    		@Override
    		protected void onCreate(Bundle savedInstanceState) {
        		super.onCreate(savedInstanceState);
        		InjectUtils.injectActivity(this);//注入
    		}

		    @Click({R.id.bt,R.id.bt2})//为方法绑定点击控件
		    public void clickBt(View pView){
		        switch (pView.getId()){
		            case R.id.bt1:
		                Log.e("yzy", "clickBt1");
		                break;
		            case R.id.bt2:
		                Log.e("yzy", "clickBt2");
		                break;
		            default:
		        }
		    }
		
		}
		
		/***************************fragment*********************************/
		
		@ContentView(R.layout.fragment_layout)
        public class MyFragment extends Fragment {
            @ViewInject(value = R.id.fragment_tv,text = "我是fragment")
            TextView mTextView;
        
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                return InjectUtils.injectFragment(this);
            }
        
            @Click({R.id.fragment_tv,R.id.fragment_bt})
            public void onClick(View pView){
                switch(pView.getId()){
                    case R.id.fragment_tv:
                        Log.e("yzy", "onClick: tv----------");
                        break;
                    case R.id.fragment_bt:
                        Log.e("yzy", "onClick: bt----------");
                        break;
                    default:
                }
            }
        }
	
	
