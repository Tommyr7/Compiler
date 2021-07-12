// Generated from C:/Users/Tommy/Desktop/Compiler/src/parser\Mx.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#programs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograms(MxParser.ProgramsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#programsimple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramsimple(MxParser.ProgramsimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefsimple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefsimple(MxParser.VarDefsimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#paraList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaList(MxParser.ParaListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPara(MxParser.ParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDefstruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefstruct(MxParser.ClassDefstructContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartstate(MxParser.PartstateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDefstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefstate(MxParser.VarDefstateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstate(MxParser.IfstateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForstate(MxParser.ForstateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whilestate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhilestate(MxParser.WhilestateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnstate(MxParser.ReturnstateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continuestate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuestate(MxParser.ContinuestateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakstate(MxParser.BreakstateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprstate(MxParser.ExprstateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullstate}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullstate(MxParser.NullstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPart(MxParser.PartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code errorcreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErrorcreator(MxParser.ErrorcreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arraycreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraycreator(MxParser.ArraycreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classcreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClasscreator(MxParser.ClasscreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basiccreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasiccreator(MxParser.BasiccreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstexpression(MxParser.ConstexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suffixexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixexpression(MxParser.SuffixexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewexpression(MxParser.NewexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassexpression(MxParser.ClassexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisexpression(MxParser.ThisexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubexpression(MxParser.SubexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdexpression(MxParser.IdexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prefixexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixexpression(MxParser.PrefixexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrexpression(MxParser.ArrexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryexpression(MxParser.BinaryexpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcexpression}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncexpression(MxParser.FuncexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#basictype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasictype(MxParser.BasictypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returntype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturntype(MxParser.ReturntypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MxParser.ConstantContext ctx);
}