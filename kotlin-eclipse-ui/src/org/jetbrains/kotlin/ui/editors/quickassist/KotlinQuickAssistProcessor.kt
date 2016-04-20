package org.jetbrains.kotlin.ui.editors.quickassist

import org.eclipse.core.runtime.CoreException
import org.eclipse.jdt.ui.text.java.IInvocationContext
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal
import org.eclipse.jdt.ui.text.java.IProblemLocation
import org.eclipse.jdt.ui.text.java.IQuickAssistProcessor
import com.google.common.collect.Lists
import java.util.ArrayList

object KotlinQuickAssistProcessor : IQuickAssistProcessor {
    override public fun hasAssists(context: IInvocationContext) : Boolean = getAssists(context, null).isNotEmpty()
    
    override public fun getAssists(context: IInvocationContext?, locations: Array<IProblemLocation>?) : Array<IJavaCompletionProposal> {
        val allApplicableProposals = ArrayList<IJavaCompletionProposal>()
        
        getSingleKotlinQuickAssistProposals().filterTo(allApplicableProposals) { it.isApplicable() }
        
        return allApplicableProposals.toTypedArray()
    }
    
    private fun getSingleKotlinQuickAssistProposals() : List<KotlinQuickAssistProposal> {
        return listOf(
            KotlinReplaceGetAssistProposal(), 
            KotlinSpecifyTypeAssistProposal(),
            KotlinRemoveExplicitTypeAssistProposal(),
            KotlinImplementMethodsProposal(),
            KotlinConvertToExpressionBodyAssistProposal(),
            KotlinConvertToBlockBodyAssistProposal(),
            KotlinChangeReturnTypeProposal())
    }
}