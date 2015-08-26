package yifeiyuan.practice.gmei;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import yifeiyuan.practice.gmei.api.ApiClient;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private Context mContext;

    private GMeiAdapter mAdapter;
    private ArrayList<GMeiZi> mMData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        setupToolbar();
        setupRefreshLayout();
        setupRecyclerView();
        mFab.setOnClickListener(v -> new Thread(() -> {
            if (mRefresh.isRefreshing()) {
                return;
            }
            getData(REFRESH);
        }).start());

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setupRefreshLayout() {
        mRefresh.setColorSchemeColors(R.color.primary, R.color.accent, R.color.primary_dark);
        mRefresh.setOnRefreshListener(() -> new Thread(() -> {
            getData(REFRESH);
        }).start());
    }

    private void setupRecyclerView() {

        mMData = new ArrayList<>();
        mAdapter = new GMeiAdapter(mMData, mContext, new GMeiAdapter.OnGMeiClickListener() {
            @Override
            public void onClick(GMeiZi gmei, int position) {

            }
        });

//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRv.setLayoutManager(manager);
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mAdapter);

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && manager.findLastVisibleItemPosition() == mMData.size()-1 && !mRefresh.isRefreshing()) {
                    mRefresh.setRefreshing(true);
                    new Thread(() -> getData(LOADMORE)).start();
                }
            }
        });

        mRv.postDelayed(() -> {
            mRefresh.setRefreshing(true);
            new Thread(() -> getData(REFRESH)).start();
        }, 300);
    }

    public static final int REFRESH = 1;
    public static final int LOADMORE = 2;
    private int currPage = 1;

    private void getData(int mode) {
        if (mode == REFRESH) {
            currPage = 1;
        } else {
            currPage += 1;
        }
        GMeiResult result = ApiClient.getGMeiService().getMeizi("福利", currPage);
        if (!result.error) {
            if (mode == REFRESH) {
                mMData.clear();
            }
            mMData.addAll(result.results);
            runOnUiThread(() -> {
                        mRefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
            );
        }
        Log.d(TAG, "getData result:" + result.toString() + ";UIThread?" + Thread.currentThread());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
