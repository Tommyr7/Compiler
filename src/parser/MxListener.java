// Generated from C:/Users/Tommy/Desktop/Compiler/src/parser\Mx.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#programs}.
	 * @param ctx the parse tree
	 */
	void enterPrograms(MxParser.ProgramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#programs}.
	 * @param ctx the parse tree
	 */
	void exitPrograms(MxParser.ProgramsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#programsimple}.
	 * @param ctx the parse tree
	 */
	void enterProgramsimple(MxParser.ProgramsimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#programsimple}.
	 * @param ctx the parse tree
	 */
	void exitProgramsimple(MxParser.ProgramsimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefsimple}.
	 * @param ctx the parse tree
	 */
	void enterVarDefsimple(MxParser.VarDefsimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefsimple}.
	 * @param ctx the parse tree
	 */
	void exitVarDefsimple(MxParser.VarDefsimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#paraList}.
	 * @param ctx the parse tree
	 */
	void enterParaList(MxParser.ParaListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#paraList}.
	 * @param ctx the parse tree
	 */
	void exitParaList(MxParser.ParaListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#para}.
	 * @param ctx the parse tree
	 */
	void enterPara(MxParser.ParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#para}.
	 * @param ctx the parse tree
	 */
	void exitPara(MxParser.ParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDefstruct}.
	 * @param ctx the parse tree
	 */
	void enterClassDefstruct(MxParser.ClassDefstructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDefstruct}.
	 * @param ctx the parse tree
	 */
	void exitClassDefstruct(MxParser.ClassDefstructContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPartstate(MxParser.PartstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPartstate(MxParser.PartstateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDefstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDefstate(MxParser.VarDefstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDefstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDefstate(MxParser.VarDefstateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfstate(MxParser.IfstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfstate(MxParser.IfstateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForstate(MxParser.ForstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForstate(MxParser.ForstateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whilestate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhilestate(MxParser.WhilestateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whilestate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhilestate(MxParser.WhilestateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnstate(MxParser.ReturnstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnstate(MxParser.ReturnstateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continuestate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinuestate(MxParser.ContinuestateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continuestate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinuestate(MxParser.ContinuestateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakstate(MxParser.BreakstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakstate(MxParser.BreakstateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprstate(MxParser.ExprstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprstate(MxParser.ExprstateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterNullstate(MxParser.NullstateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitNullstate(MxParser.NullstateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#part}.
	 * @param ctx the parse tree
	 */
	void enterPart(MxParser.PartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#part}.
	 * @param ctx the parse tree
	 */
	void exitPart(MxParser.PartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code errorcreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterErrorcreator(MxParser.ErrorcreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code errorcreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitErrorcreator(MxParser.ErrorcreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arraycreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterArraycreator(MxParser.ArraycreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arraycreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitArraycreator(MxParser.ArraycreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classcreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterClasscreator(MxParser.ClasscreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classcreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitClasscreator(MxParser.ClasscreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basiccreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterBasiccreator(MxParser.BasiccreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basiccreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitBasiccreator(MxParser.BasiccreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstexpression(MxParser.ConstexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstexpression(MxParser.ConstexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixexpression(MxParser.SuffixexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixexpression(MxParser.SuffixexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewexpression(MxParser.NewexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewexpression(MxParser.NewexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterClassexpression(MxParser.ClassexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitClassexpression(MxParser.ClassexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisexpression(MxParser.ThisexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisexpression(MxParser.ThisexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubexpression(MxParser.SubexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubexpression(MxParser.SubexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdexpression(MxParser.IdexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdexpression(MxParser.IdexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixexpression(MxParser.PrefixexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixexpression(MxParser.PrefixexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrexpression(MxParser.ArrexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrexpression(MxParser.ArrexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryexpression(MxParser.BinaryexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryexpression(MxParser.BinaryexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncexpression(MxParser.FuncexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncexpression(MxParser.FuncexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#basictype}.
	 * @param ctx the parse tree
	 */
	void enterBasictype(MxParser.BasictypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#basictype}.
	 * @param ctx the parse tree
	 */
	void exitBasictype(MxParser.BasictypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returntype}.
	 * @param ctx the parse tree
	 */
	void enterReturntype(MxParser.ReturntypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returntype}.
	 * @param ctx the parse tree
	 */
	void exitReturntype(MxParser.ReturntypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MxParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MxParser.ConstantContext ctx);
}