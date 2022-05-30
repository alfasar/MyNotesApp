package com.example.mynotesapp

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class BirthdayWorker(val context: Context, val workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString()
        )
        return Result.success()
    }
}