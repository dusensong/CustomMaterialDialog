package cn.reflect.demo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.reflect.dialogs.fragment.DatePickerDialogFragment;
import cn.reflect.dialogs.fragment.ListDialogFragment;
import cn.reflect.dialogs.fragment.ProgressDialogFragment;
import cn.reflect.dialogs.fragment.SimpleDialogFragment;
import cn.reflect.dialogs.iface.IDateDialogListener;
import cn.reflect.dialogs.iface.IListDialogListener;
import cn.reflect.dialogs.iface.IMultiChoiceListDialogListener;
import cn.reflect.dialogs.iface.ISimpleDialogCancelListener;
import cn.reflect.dialogs.iface.ISimpleDialogListener;

public class MainActivity extends ActionBarActivity implements
        ISimpleDialogListener,
        IDateDialogListener,
        ISimpleDialogCancelListener,
        IListDialogListener,
        IMultiChoiceListDialogListener {

    private static final int REQUEST_PROGRESS = 1;
    private static final int REQUEST_LIST_SIMPLE = 9;
    private static final int REQUEST_LIST_MULTIPLE = 10;
    private static final int REQUEST_LIST_SINGLE = 11;
    private static final int REQUEST_DATE_PICKER = 12;
    private static final int REQUEST_TIME_PICKER = 13;
    private static final int REQUEST_SIMPLE_DIALOG = 42;

    MainActivity c = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.img_avast_logo_small);

        findViewById(R.id.message_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment.createBuilder(c, getSupportFragmentManager())
                        .setMessage("这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框")
                        .setPositiveButtonText("确定")
                        .setNegativeButtonText("取消")
                        .show();
            }
        });
        findViewById(R.id.message_title_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment.createBuilder(c, getSupportFragmentManager())
                        .setTitle("升级提示")
                        .setMessage
                                ("这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框\n\n" +
                                        "这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框\n\n" +
                                        "这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框\n\n" +
                                        "这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框\n\n" +
                                        "这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框\n\n" +
                                        "这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框")
                        .setNegativeButtonText("关闭")
                        .show();
            }
        });
        findViewById(R.id.message_title_buttons_dialog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimpleDialogFragment.createBuilder(c, getSupportFragmentManager())
                                .setTitle("这是一个对话框")
                                .setMessage("这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框，这是一个文本对话框")
                                .setPositiveButtonText("喜欢")
                                .setNegativeButtonText("不喜欢")
                                .setRequestCode(REQUEST_SIMPLE_DIALOG)
                                .show();
                    }
                });
        findViewById(R.id.long_buttons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment.createBuilder(c, getSupportFragmentManager())
                        .setMessage("How will you decide?")
                        .setPositiveButtonText("Time for some thrillin' heroics!")
                        .setNegativeButtonText("Misbehave")
                        .setNeutralButtonText("Keep flying").show();
            }
        });

        findViewById(R.id.list_dialog_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListDialogFragment
                        .createBuilder(c, getSupportFragmentManager())
                        .setTitle("你喜欢的颜色:")
                        .setItems(new String[]{"这是一个很长很长的白色白色白色白色白色", "白色", "红色",
                                "黄色", "黑色", "绿色"})
                        .setRequestCode(REQUEST_LIST_SIMPLE)
                        .show();

            }
        });
        findViewById(R.id.list_dialog_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListDialogFragment
                        .createBuilder(c, getSupportFragmentManager())
                        .setTitle("你喜欢的演员：")
                        .setItems(new String[]{"AAAA", "BBBB", "CCCC",
                                "DDDD", "EEEE", "FFFF", "GGGGG", "HHHH", "IIII", "JJJJ", "KKKK", "LLLL"})
                        .setRequestCode(REQUEST_LIST_SINGLE)
                        .setChoiceMode(AbsListView.CHOICE_MODE_SINGLE)
                        .show();

            }
        });
        findViewById(R.id.list_dialog_multiple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListDialogFragment
                        .createBuilder(c, getSupportFragmentManager())
                        .setTitle("你喜欢的演员：")
                        .setItems(new String[]{"第一个", "第二个", "第三个",
                                "第四个", "第五个", "第六个"})
                        .setRequestCode(REQUEST_LIST_MULTIPLE)
                        .setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE)
                        .setCheckedItems(new int[]{1, 3})
                        .show();

            }
        });

        findViewById(R.id.progress_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialogFragment.createBuilder(c, getSupportFragmentManager())
                        .setMessage("正在努力加载数据中，请稍等...")
                        .setRequestCode(REQUEST_PROGRESS)
                        .show();
            }
        });
//        findViewById(R.id.custom_dialog).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                JayneHatDialogFragment.show(c);
//            }
//        });
//        findViewById(R.id.time_picker).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimePickerDialogFragment
//                        .createBuilder(DemoActivity.this, getSupportFragmentManager())
//                        .setDate(new Date())
//                        .setPositiveButtonText(android.R.string.ok)
//                        .setNegativeButtonText(android.R.string.cancel)
//                        .setRequestCode(REQUEST_TIME_PICKER)
//                        .show();
//            }
//        });
        findViewById(R.id.date_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2000, 6, 6);
                DatePickerDialogFragment
                        .createBuilder(MainActivity.this, getSupportFragmentManager())
                        .setTitle("选择时间")
                        .setDate(calendar.getTime())
                        .setPositiveButtonText(android.R.string.ok)
                        .setNegativeButtonText(android.R.string.cancel)
                        .setRequestCode(REQUEST_DATE_PICKER)
                        .show();
            }
        });
    }

    // IListDialogListener

    @Override
    public void onListItemSelected(CharSequence value, int number, int requestCode) {
        if (requestCode == REQUEST_LIST_SIMPLE || requestCode == REQUEST_LIST_SINGLE) {
            Toast.makeText(c, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListItemsSelected(CharSequence[] values, int[] selectedPositions, int requestCode) {
        if (requestCode == REQUEST_LIST_MULTIPLE) {
            StringBuilder sb = new StringBuilder();
            for (CharSequence value : values) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(value);

            }
            Toast.makeText(c, "Selected: " + sb.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    // ISimpleDialogCancelListener

    @Override
    public void onCancelled(int requestCode) {
        switch (requestCode) {
            case REQUEST_SIMPLE_DIALOG:
                Toast.makeText(c, "Dialog cancelled", Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_PROGRESS:
                Toast.makeText(c, "Progress dialog cancelled", Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_LIST_SIMPLE:
            case REQUEST_LIST_SINGLE:
            case REQUEST_LIST_MULTIPLE:
                Toast.makeText(c, "Nothing selected", Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_DATE_PICKER:
                Toast.makeText(c, "Date picker cancelled", Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_TIME_PICKER:
                Toast.makeText(c, "Time picker cancelled", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // ISimpleDialogListener

    @Override
    public void onPositiveButtonClicked(int requestCode) {
        if (requestCode == REQUEST_SIMPLE_DIALOG) {
            Toast.makeText(c, "Positive button clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNegativeButtonClicked(int requestCode) {
        if (requestCode == REQUEST_SIMPLE_DIALOG) {
            Toast.makeText(c, "Negative button clicked", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_DATE_PICKER) {
            Toast.makeText(this, "Date Picker Dialog Cancelled", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_TIME_PICKER) {
            Toast.makeText(this, "Time Picker Dialog Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNeutralButtonClicked(int requestCode) {
        if (requestCode == REQUEST_SIMPLE_DIALOG) {
            Toast.makeText(c, "Neutral button clicked", Toast.LENGTH_SHORT).show();
        }
    }

    // IDateDialogListener
    @Override
    public void onPositiveButtonClicked(int resultCode, int year, int monthOfYear, int dayOfMonth) {
        String text = "";
        if (resultCode == REQUEST_DATE_PICKER) {
            text = "Date ";
        } else if (resultCode == REQUEST_TIME_PICKER) {
            text = "Time ";
        }

        Calendar cl = Calendar.getInstance();
        cl.clear();
        cl.set(year, monthOfYear, dayOfMonth);
        Toast.makeText(this, text + "Success! " + new SimpleDateFormat("yyyy-MM-dd").format(cl.getTime()), Toast.LENGTH_SHORT).show();
    }
}
