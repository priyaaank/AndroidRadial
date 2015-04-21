package radial.thoughtworks.com.radial;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class RadialActivity extends ActionBarActivity implements AbsListView.OnScrollListener {

    private ListView listView = null;
    private LinearLayout radialViewContainer;
    private int originalHeaderHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radial);
        originalHeaderHeight = getResources().getDimensionPixelSize(R.dimen.graph_height);
        radialViewContainer = (LinearLayout) findViewById(R.id.chart_container);
        listView = (ListView) findViewById(R.id.p_list);
        listView.setOnScrollListener(this);
        String[] strings = {"Auf Wiedersehen", "Der Autor", "Das Kind", "Der Mann", "Ratgeber", "Der Fahrer", "Der Weber", "Haus", "Wonhung", "Hochzeit", "Der Autor", "Das Kind", "Der Mann", "Ratgeber", "Der Fahrer", "Der Weber", "Haus", "Wonhung", "Hochzeit", "Der Autor", "Das Kind", "Der Mann", "Ratgeber", "Der Fahrer", "Der Weber", "Haus", "Wonhung", "Hochzeit", "Hersteller", "Arzte"};
        ListAdapter stringAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(stringAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_radial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        int scrolledHeight = getScrollY(absListView);
        if(radialViewContainer != null) {
            ViewGroup.LayoutParams x = radialViewContainer.getLayoutParams();
            int h = getVal(originalHeaderHeight - scrolledHeight);
            radialViewContainer.setLayoutParams(new RelativeLayout.LayoutParams(x.width, h));
            ((ViewGroup.MarginLayoutParams)listView.getLayoutParams()).setMargins(0, h, 0, 0);
        }
    }

    private int getVal(int i) {
        return i < 120 ? 120 : i;
    }

    private int getScrollY(AbsListView listView) {
        View firstChild = listView.getChildAt(0);
        if(firstChild != null) {
            return (firstChild.getHeight() * listView.getFirstVisiblePosition());
        }
        return 0;
    }

}
