package org.schabi.newpipe.settings.custom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import org.schabi.newpipe.R;

public class EditColorPreference extends EditTextPreference
        implements Preference.OnPreferenceChangeListener {
    private PreferenceViewHolder viewHolder;

    public EditColorPreference(final Context context, final AttributeSet attrs,
                               final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public EditColorPreference(final Context context, final AttributeSet attrs,
                               final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public EditColorPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditColorPreference(final Context context) {
        super(context);
        init();
    }

    private void init() {
        setWidgetLayoutResource(R.layout.preference_edit_color);
        setOnPreferenceChangeListener(this);
    }

    @Override
    public void onBindViewHolder(final PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        viewHolder = holder;

        String colorStr =
                getPreferenceManager()
                        .getSharedPreferences()
                        .getString(getKey(), null);

        if (colorStr == null) {
            return;
        }

        int color = Color.parseColor(colorStr);

        View view = viewHolder.findViewById(R.id.segment_color_view);
        view.setBackgroundColor(color);
    }

    @Override
    public boolean onPreferenceChange(final Preference preference, final Object newValue) {
        try {
            int color = Color.parseColor((String) newValue);

            View view = viewHolder.findViewById(R.id.segment_color_view);
            view.setBackgroundColor(color);

            return true;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Invalid color", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
