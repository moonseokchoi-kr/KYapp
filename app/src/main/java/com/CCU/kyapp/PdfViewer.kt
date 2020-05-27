package com.CCU.kyapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.listener.OnErrorListener
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.android.synthetic.main.activity_pdfview.*
import java.io.File

class PdfViewer : AppCompatActivity(),OnPageChangeListener,OnLoadCompleteListener {
    private val pageNumber :Int = 8
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfview)
        showPdf()
    }
    private fun showPdf(): Unit {
        val file = File(intent.getStringExtra("filepath"))
        Log.d("Filepath","filePath : ${intent.getStringExtra("filepath")}")
        Log.d("File is exist?","File is exist? "+file!!.exists());
                pdfView.fromFile(file).pages(0, 2, 1, 3, 3, 3)
                    .enableSwipe(true)
                    .enableDoubletap(true)
                    .defaultPage(pageNumber)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .onPageChange(OnPageChangeListener { page, pageCount -> onPageChanged(page,pageCount) })
                    .onError(OnErrorListener { t->Log.d("Error", "Can't Load file" )
                        t.printStackTrace()})
                    .scrollHandle(object : DefaultScrollHandle(this) {})
                    .load()
    }


    override fun onPageChanged(page: Int, pageCount: Int) {

        title = String.format("%s %s / %s", "2021년 창의융합대학 홍보자료", page+1, pageCount-8)
    }

    override fun loadComplete(nbPages: Int) {
        val meta : com.shockwave.pdfium.PdfDocument.Meta = pdfView.documentMeta
        Log.e("PDF", "title = " + meta.title);
        Log.e("PDF", "author = " + meta.author);
        Log.e("PDF", "subject = " + meta.subject);
        Log.e("PDF", "keywords = " + meta.keywords);
        Log.e("PDF", "creator = " + meta.creator);
        Log.e("PDF", "producer = " + meta.producer);
        Log.e("PDF", "creationDate = " + meta.creationDate);
        Log.e("PDF", "modDate = " + meta.modDate);

        printBookmarksTree(pdfView.tableOfContents, "-");

    }
    private fun printBookmarksTree(
        tableOfContents: MutableList<com.shockwave.pdfium.PdfDocument.Bookmark>,
        s: String
    ) {
        tableOfContents.forEach{
            Log.e("PDF", String.format("%s %s, p %d", s, it.title, it.pageIdx));

            if (it.hasChildren()) {

                printBookmarksTree(it.children, "$s-");
            }}

    }
}