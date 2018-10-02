package com.example.kalle.workoutdiary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class BottomNaviClass extends AppCompatActivity {

    static int green = Color.rgb(4, 168, 46);
    static int grey = Color.rgb(40, 40, 45);
    static int red = Color.rgb(176, 14, 35);
    static int darkgrey = Color.rgb(30, 30, 30);
    static int black = Color.rgb(0, 0, 0);
    static int lightBlack = Color.rgb(20,20,22);
    static int superLightBlack = Color.rgb(10,10,10);

    public static int height;
    public static int width;
    public static View view;
    LinearLayout bar;
    LinearLayout viewContainer;
    LinearLayout pageHolder;
    LinearLayout page;

    LinearLayout.LayoutParams bottomPara;

    public static int id;
    static int identifier = 3;
    static  boolean hasLoadedOnce = false;

    LinearLayout banner;

    private static final String TAG = "MainActivity";

    private AdView mAdView;


    static boolean inWeightPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
        hasLoadedOnce = true;
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
    }

    public void setUp() {
        System.out.println("This is current id: " + id);
        bar = new LinearLayout(this);
        viewContainer = new LinearLayout(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        view = new View(this);
        pageHolder = new LinearLayout(this);
        banner = new LinearLayout(this);
        banner.setBackgroundColor(Color.BLACK);
        banner.setMinimumWidth(width);



        AdView adView = new AdView(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.loadAd(adRequest);

        LinearLayout.LayoutParams topPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        makeTopgravityparams(topPara);


        bottomPara = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.BOTTOM);
        viewContainer.setLayoutParams(bottomPara);
        viewContainer.setOrientation(LinearLayout.VERTICAL);
        makeNav();
        if (hasLoadedOnce == false) {
            createSpecific(2);
        }

        if (hasLoadedOnce) {
            createSpecific(identifier);
        }

        LinearLayout.LayoutParams buttP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        SetUp.makeButtonAndTextRowParams(buttP);

        banner.addView(adView, buttP);

        pageHolder.setLayoutParams(bottomPara);
        viewContainer.addView(banner);
        viewContainer.addView(pageHolder);
        viewContainer.addView(bar);
        setContentView(viewContainer);
        changeNavBar(identifier);

    }
    public static void makeTopgravityparams(LinearLayout.LayoutParams params) {
        params.gravity = Gravity.TOP;
    }

    public void makeNav() {
        BottomNav.makeBottomnavBar(bar, width, view.getContext(), 3);
        BottomNav.stats.setOnClickListener(new NavBarOnClickListener1(0));
        BottomNav.profile.setOnClickListener(new NavBarOnClickListener1(1));
        BottomNav.addWorkout.setOnClickListener(new NavBarOnClickListener1(2));
        BottomNav.diary.setOnClickListener(new NavBarOnClickListener1(3));
        BottomNav.bodyStats.setOnClickListener(new NavBarOnClickListener1(4));
    }

    public void createSpecific(int identifier) {
        if (identifier == 0) {
            System.out.println("identity " + identifier );
            pageHolder.removeAllViews();
            if (inWeightPage) {
                makeWeightGraph();
            } else {
                makeStatChooser();
            }
            pageHolder.addView(page);
            setSelectedNavButtonColor(0);

        }
        if (identifier == 1) {
            System.out.println("identity " + identifier );
            pageHolder.removeAllViews();
            page = new LinearLayout(this);
            page.setBackgroundColor(Color.GREEN);
            page.setLayoutParams(bottomPara);
            pageHolder.addView(page);
            setSelectedNavButtonColor(1);

        }
        if (identifier == 2) {
            System.out.println("identity " + identifier );
            pageHolder.removeAllViews();
            makeAddWorkout();
            pageHolder.addView(page);
            setSelectedNavButtonColor(2);

        }
        if (identifier == 3) {
            System.out.println("identity " + identifier );
            pageHolder.removeAllViews();
            makeDiary();
            pageHolder.addView(page);
            setSelectedNavButtonColor(3);

        }
        if (identifier == 4) {
            System.out.println("identity " + identifier );
            pageHolder.removeAllViews();
            makeBodyStat();
            pageHolder.addView(page);
            setSelectedNavButtonColor(4);

        }
    }
    int g;
    public void setSelectedNavButtonColor(int caller)  {
        int row = caller;

        System.out.println("Tryin to get: " + row);

        BottomNav.buttonList.get(row).setBackgroundColor(superLightBlack);

        if (g != row) {
            System.out.println("Numberd: " + g);
            BottomNav.buttonList.get(g).setBackgroundColor(lightBlack);
            g = row;
            System.out.println("Numberd: " + g);
        } else {
            System.out.println("Number1: " + g);
            g = row;
        }

    }

    public void makeDiary() {
        page = new LinearLayout(this);
        DiaryClass.createLayout(view, page);
        page.setLayoutParams(bottomPara);
    }

    public void makeStatChooser() {
           page = new LinearLayout(this);
           StatChooserClass.createLayout(view, page);
           page.setLayoutParams(bottomPara);
    }

    public void makeBodyStat() {
        page = new LinearLayout(this);
        BodyStatClass.createLayout(view, page);
        page.setLayoutParams(bottomPara);
    }

    public void makeWeightGraph() {
        page = new LinearLayout(this);
        BodyGraphClass.createLayout(view, page);
        page.setLayoutParams(bottomPara);
    }

    public void makeAddWorkout() {
        page = new LinearLayout(this);
        AddWorkoutClass.createLayout(view, page);
        page.setLayoutParams(bottomPara);
    }

    public void changeNavBar(int identity) {
        if (identity == 4 || inWeightPage == true) {
            for (int i = 0; i < BottomNav.buttonList.size(); i++ ) {
                BottomNav.buttonList.get(i).setColorFilter(red);

                if (i == 0) {
                    BottomNav.buttonList.get(i).setImageResource(R.drawable.weigthstat_icon);
                }
            }
        } else {
            for (int i = 0; i < BottomNav.buttonList.size(); i++ ) {
                BottomNav.buttonList.get(i).setColorFilter(green);

                if (i == 0) {
                    BottomNav.buttonList.get(i).setImageResource(R.drawable.stats_image);
                }
            }
        }

    }


    class NavBarOnClickListener1 implements View.OnClickListener {

        int ids;

        public NavBarOnClickListener1(int name) {
            ids = name;
        }

        public NavBarOnClickListener1() {
        }

        @Override
        public void onClick(View v) {
            System.out.println("Row " + ids + " clicked");
            Context context = v.getContext();
            System.out.println("id " + id );
            SaveWorkout.checkExistingFiles(v.getContext());
            SaveBodyWeight.checkExistingFiles(v.getContext());




            if (ids == 0) {
                BottomNaviClass.id = ids;
                System.out.println("ids " + ids );
                createSpecific(ids);
                changeNavBar(ids);
                System.out.println("id " + id );

            } if (ids == 1) {
                inWeightPage = false;
                System.out.println("ids " + ids );
                BottomNaviClass.id = ids;
                createSpecific(ids);
                changeNavBar(ids);
                System.out.println("id " + id );
            } if (ids == 2) {
                inWeightPage = false;
                System.out.println("ids " + ids );
                BottomNaviClass.id = ids;
                createSpecific(ids);
                changeNavBar(ids);
                System.out.println("id " + id );
            } if (ids == 3) {
                inWeightPage = false;
                BottomNaviClass.id = ids;
                System.out.println("ids " + ids );
                createSpecific(ids);
                changeNavBar(ids);
                System.out.println("id " + id );
            } if (ids == 4) {
                inWeightPage = true;
                System.out.println("ids " + ids );
                BottomNaviClass.id = ids;
                createSpecific(ids);
                changeNavBar(ids);
                System.out.println("id " + id );
            }

        }
    }
}
