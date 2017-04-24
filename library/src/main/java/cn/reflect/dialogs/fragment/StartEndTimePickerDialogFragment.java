package cn.reflect.dialogs.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

import com.lany.picker.StartEndTimePicker;

import java.util.List;

import cn.reflect.dialogs.R;
import cn.reflect.dialogs.iface.IStartEndTimePickerDialogListener;
import cn.reflect.dialogs.iface.ITimePickerDialogListener;

/**
 * Dialog with a time picker.
 * <p/>
 * Implement {@link ITimePickerDialogListener} to handle events.
 */
public class StartEndTimePickerDialogFragment extends DatePickerDialogFragment {

    private StartEndTimePicker mStartEndTimePicker;

    public static SimpleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {
        return new SimpleDialogBuilder(context, fragmentManager, StartEndTimePickerDialogFragment.class);
    }

    private List<IStartEndTimePickerDialogListener> getStartEndTimePickerDialogListeners() {
        return getDialogListeners(IStartEndTimePickerDialogListener.class);
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
                    for (IStartEndTimePickerDialogListener listener : getStartEndTimePickerDialogListeners()) {
                        listener.onPositiveButtonClicked(mRequestCode,
                                mStartEndTimePicker.getCurrentStartHour(), mStartEndTimePicker.getCurrentStartMinute(),
                                mStartEndTimePicker.getCurrentEndHour(), mStartEndTimePicker.getCurrentEndMinute());
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
                    for (IStartEndTimePickerDialogListener listener : getStartEndTimePickerDialogListeners()) {
                        listener.onNegativeButtonClicked(mRequestCode);
                    }
                    dismiss();
                }
            });
        }

        mStartEndTimePicker = (StartEndTimePicker) builder.getLayoutInflater().inflate(R.layout.sdl_start_end_time_picker, null);
        builder.setView(mStartEndTimePicker);

        return builder;
    }

}
