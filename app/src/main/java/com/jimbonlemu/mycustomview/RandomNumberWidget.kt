package com.jimbonlemu.mycustomview

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews

class RandomNumberWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action == WIDGET_CLICK) {

            val views = RemoteViews(
                context.packageName,
                R.layout.random_number_widget
            )
            views.setTextViewText(R.id.appwidget_text, "Random" + NumberGenerator.generate(100))

            AppWidgetManager.getInstance(context)
                .updateAppWidget(intent.getIntExtra(WIDGET_ID_EXTRA, 0), views)

        }
    }

    override fun onEnabled(context: Context) {
    }

    override fun onDisabled(context: Context) {
    }

    companion object {
        const val WIDGET_CLICK = "android.appwidget.action.APPWIDGET_UPDATE"
        const val WIDGET_ID_EXTRA = "widget_id_extra"
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val intent = Intent(context, RandomNumberWidget::class.java).apply {
        action = RandomNumberWidget.WIDGET_CLICK
        putExtra(RandomNumberWidget.WIDGET_ID_EXTRA, appWidgetId)
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context, appWidgetId, intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        } else {
            0
        }
    )
    val lastUpdate = "Random" + NumberGenerator.generate(100)
    val views = RemoteViews(context.packageName, R.layout.random_number_widget)
    views.setTextViewText(R.id.appwidget_text, lastUpdate)
    views.setOnClickPendingIntent(R.id.btn_click, pendingIntent)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}