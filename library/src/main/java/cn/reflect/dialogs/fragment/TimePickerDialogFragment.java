package cn.reflect.dialogs.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

import com.lany.picker.HMPicker;

import java.util.Calendar;
import java.util.List;

import cn.reflect.dialogs.R;
import cn.reflect.dialogs.core.BaseDialogFragment;
import cn.reflect.dialogs.iface.ITimePickerDialogListener;

/**
 * Dialog with a time picker.
 * <p/>
 * Implement {@link cn.reflect.dialogs.iface.ITimePickerDialogListener} to handle events.
 */
public class TimePickerDialogFragment extends DatePickerDialogFragment {

    private HMPicker mHMPicker;
    private Calendar mCalendar;

    public static SimpleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {
        return new SimpleDialogBuilder(context, fragmentManager, TimePickerDialogFragment.class);
    }


    private List<ITimePickerDialogListener> getTimePickerDialogListeners() {
        return getDialogListeners(ITimePickerDialogListener.class);
    }

    @Override
    protected BaseDialogFragment.Builder build(BaseDialogFragment.Builder builder) {
        final CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        final CharSequence positiveButtonText = getPositiveButtonText();
        if (!TextUtils.isEmpty(positiveButtonText)) {
            builder.setPositiveButton(positiveButtonText, new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    for (ITimePickerDialogListener listener : getTimePickerDialogListeners()) {
                        listener.onPositiveButtonClicked(mRequestCode, mHMPicker.getCurrentHour(), mHMPicker.getCurrentMinute());
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
                    for (ITimePickerDialogListener listener : getTimePickerDialogListeners()) {
                        listener.onNegativeButtonClicked(mRequestCode);
                    }
                    dismiss();
                }
            });
        }

        mHMPicker = (HMPicker) builder.getLayoutInflater().inflate(R.layout.sdl_timepicker, null);
        builder.setView(mHMPicker);

        mHMPicker.setIs24HourView(true);
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(getArguments().getLong(ARG_DATE, System.currentTimeMillis()));
        mHMPicker.setCurrentHour(mCalendar.get(Calendar.HOUR_OF_DAY));
        mHMPicker.setCurrentMinute(mCalendar.get(Calendar.MINUTE));
        return builder;
    }

}
