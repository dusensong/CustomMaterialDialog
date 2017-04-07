package cn.reflect.dialogs.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

import com.lany.picker.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.reflect.dialogs.R;
import cn.reflect.dialogs.core.BaseDialogBuilder;
import cn.reflect.dialogs.core.BaseDialogFragment;
import cn.reflect.dialogs.iface.IDatePickerDialogListener;

/**
 * Dialog with a date picker.
 * <p/>
 * Implement {@link IDatePickerDialogListener} to handle events.
 */
public class DatePickerDialogFragment extends BaseDialogFragment {

    protected static final String ARG_TITLE = "title";
    protected static final String ARG_POSITIVE_BUTTON = "positive_button";
    protected static final String ARG_NEGATIVE_BUTTON = "negative_button";
    protected static final String ARG_DATE = "date";

    private DatePicker mDatePicker;
    private Calendar mCalendar;

    public static SimpleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {
        return new SimpleDialogBuilder(context, fragmentManager, DatePickerDialogFragment.class);
    }

    private List<IDatePickerDialogListener> getDatePickerDialogListeners() {
        return getDialogListeners(IDatePickerDialogListener.class);
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
                    for (IDatePickerDialogListener listener : getDatePickerDialogListeners()) {
                        listener.onPositiveButtonClicked(mRequestCode,
                                mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
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
                    for (IDatePickerDialogListener listener : getDatePickerDialogListeners()) {
                        listener.onNegativeButtonClicked(mRequestCode);
                    }
                    dismiss();
                }
            });
        }
        mDatePicker = (DatePicker) builder.getLayoutInflater().inflate(R.layout.sdl_datepicker, null);
        builder.setView(mDatePicker);

        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(getArguments().getLong(ARG_DATE, System.currentTimeMillis()));
        mDatePicker.updateDate(mCalendar.get(Calendar.YEAR)
                , mCalendar.get(Calendar.MONTH)
                , mCalendar.get(Calendar.DAY_OF_MONTH));
        return builder;
    }

    protected CharSequence getTitle() {
        return getArguments().getCharSequence(ARG_TITLE);
    }

    protected CharSequence getPositiveButtonText() {
        return getArguments().getCharSequence(ARG_POSITIVE_BUTTON);
    }

    protected CharSequence getNegativeButtonText() {
        return getArguments().getCharSequence(ARG_NEGATIVE_BUTTON);
    }


    public static class SimpleDialogBuilder extends BaseDialogBuilder<SimpleDialogBuilder> {
        Date mDate = new Date();

        private CharSequence mTitle;
        private CharSequence mPositiveButtonText;
        private CharSequence mNegativeButtonText;

        private boolean mShowDefaultButton = true;

        protected SimpleDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends DatePickerDialogFragment> clazz) {
            super(context, fragmentManager, clazz);
        }

        public SimpleDialogBuilder setTitle(int titleResourceId) {
            mTitle = mContext.getString(titleResourceId);
            return this;
        }

        public SimpleDialogBuilder setTitle(CharSequence title) {
            mTitle = title;
            return this;
        }

        public SimpleDialogBuilder setPositiveButtonText(int textResourceId) {
            mPositiveButtonText = mContext.getString(textResourceId);
            return this;
        }

        public SimpleDialogBuilder setPositiveButtonText(CharSequence text) {
            mPositiveButtonText = text;
            return this;
        }

        public SimpleDialogBuilder setNegativeButtonText(int textResourceId) {
            mNegativeButtonText = mContext.getString(textResourceId);
            return this;
        }

        public SimpleDialogBuilder setNegativeButtonText(CharSequence text) {
            mNegativeButtonText = text;
            return this;
        }

        public SimpleDialogBuilder setDate(Date date) {
            mDate = date;
            return this;
        }

        @Override
        protected Bundle prepareArguments() {
            Bundle args = new Bundle();
            args.putCharSequence(SimpleDialogFragment.ARG_TITLE, mTitle);
            args.putCharSequence(SimpleDialogFragment.ARG_POSITIVE_BUTTON, mPositiveButtonText);
            args.putCharSequence(SimpleDialogFragment.ARG_NEGATIVE_BUTTON, mNegativeButtonText);
            args.putLong(ARG_DATE, mDate.getTime());
            return args;
        }

        @Override
        protected SimpleDialogBuilder self() {
            return this;
        }
    }
}
