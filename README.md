# YzyViewInject
手写ButterKnife注解框架  
**使用方法**  

- gradle添加依赖compile 'com.github.yzytmac:yzyinject:1.0.0'  
- ViewUtils.inject(this)进行注入，相当于框架的初始化，必须有，在onCreate方法中  
- @ContentView(R.layout.activity_main)绑定布局
- @ViewInject(R.id.bt)绑定控件
- @Click({R.id.bt,R.id.bt2})绑定点击事件的方法，方法名随意，参数可以为空或一个view

	    @ContentView(R.layout.activity_main)//为Activity绑定布局
		public class MainActivity extends AppCompatActivity {
    		@ViewInject(R.id.bt)
    		private Button bt;//为控件绑定id

    		@Override
    		protected void onCreate(Bundle savedInstanceState) {
        		super.onCreate(savedInstanceState);
        		ViewUtils.inject(this);//注入
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
	
	
