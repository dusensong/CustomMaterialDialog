package cn.reflect.dialogs.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

import com.lany.picker.YMDHPicker;

import java.util.Calendar;
import java.util.List;

import cn.reflect.dialogs.R;
import cn.reflect.dialogs.iface.IDateTimePickerDialogListener;
import cn.reflect.dialogs.iface.ITimePickerDialogListener;

/**
 * Dialog with a time picker.
 * <p/>
 * Implement {@link ITimePickerDialogListener} to handle events.
 */
public class DateTimePickerDialogFragment extends DatePickerDialogFragment {

    private YMDHPicker mYMDHPicker;
    private Calendar mCalendar;

    public static SimpleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {
        return new SimpleDialogBuilder(context, fragmentManager, DateTimePickerDialogFragment.class);
    }

    private List<IDateTimePickerDialogListener> getDateTimePickerDialogListeners() {
        return getDialogListeners(IDateTimePickerDialogListener.class);
    }

    @Override
    protected Builder build(Builder builder) {
        final CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        final CharSequence positiveButtonText = getPositiveButtonText();
        if (!TextUtils.isEmpty(positiveButtonText)) {
            builder.setPositiveButton(positiveButtonText, new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    for (IDateTimePickerDialogListener listener : getDateTimePickerDialogListeners()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(mYMDHPicker.getYear(), mYMDHPicker.getMonth(), mYMDHPicker.getDayOfMonth(), mYMDHPicker.getHourOfDay(), 0);
                        listener.onPositiveButtonClicked(mRequestCode, calendar.getTime());
                    }
                    dismiss();
                }
            });
        }

        final CharSequence negativeButtonText = getNegativeButtonText();
        if (!TextUtils.isEmpty(negativeButtonText)) {
            builder.setNegativeButton(negativeButtonText, new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    for (IDateTimePickerDialogListener listener : getDateTimePickerDialogListeners()) {
                        listener.onNegativeButtonClicked(mRequestCode);
                    }
                    dismiss();
                }
            });
        }

        mYMDHPicker = (YMDHPicker) builder.getLayoutInflater().inflate(R.layout.sdl_ymdhpicker, null);
        builder.setView(mYMDHPicker);

        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(getArguments().getLong(ARG_DATE, System.currentTimeMillis()));
        mYMDHPicker.updateDate(mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH),
                mCalendar.get(Calendar.HOUR_OF_DAY));
        return builder;
    }

}
