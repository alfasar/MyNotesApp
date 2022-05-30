package com.example.mynotesapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

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