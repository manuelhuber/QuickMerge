package de.manuelhuber

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import git4idea.commands.GitCommand
import git4idea.commands.GitImpl
import git4idea.commands.GitLineHandler
import java.io.File


/**
 * @author Manuel Huber
 */
class QuickMergeAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        if (e == null) return
        val project = e.project!!
        pull(project, "develop")
    }

    private fun pull(project: Project, branch: String) {
        val gitRoot = LocalFileSystem.getInstance().findFileByIoFile(File(project.basePath))!!
        val h = GitLineHandler(project, gitRoot, GitCommand.PULL)
        h.setUrl(gitRoot.url)
        h.addParameters("origin")
        h.addParameters(branch)
        val git = GitImpl()
        git.runCommand(h)
    }

}
