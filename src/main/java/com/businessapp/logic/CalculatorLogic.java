package com.businessapp.logic;

import com.businessapp.Component;
import com.businessapp.ControllerIntf;
import com.businessapp.fxgui.CalculatorGUI_Intf;
import com.businessapp.fxgui.CalculatorGUI_Intf.Token;

import java.text.DecimalFormat;

import static com.businessapp.fxgui.CalculatorGUI_Intf.Token.*;

/**
 * Implementation of CalculatorLogicIntf that only displays Tokens
 * received from the Calculator UI.
 */
class CalculatorLogic implements CalculatorLogicIntf {
    private CalculatorGUI_Intf view;
    private StringBuffer dsb = new StringBuffer();
    private final double VAT_RATE = 19.0;

    private Token lastOperator = null;
    private double interimResult = 0;

    CalculatorLogic() {
    }

    @Override
    public void inject(ControllerIntf dep) {
        this.view = (CalculatorGUI_Intf) dep;
    }

    @Override
    public void inject(Component parent) {
    }

    @Override
    public void start() {
        nextToken(K_C);        
    }

    @Override
    public void stop() {
    }

    /**
     * Process next token received from UI controller.
     * <p>
     * Tokens are transformed into output into UI properties:
     * - CalculatorIntf.DISPLAY for numbers and
     * - CalculatorIntf.SIDEAREA for VAT calculations.
     * <p>
     *
     * @param tok the next Token passed from the UI, CalculatorViewController.
     */
    public void nextToken(Token tok) {
        try {
            switch (tok) {
                case K_0:
                    appendBuffer("0");
                    break;
                case K_1:
                    appendBuffer("1");
                    break;
                case K_2:
                    appendBuffer("2");
                    break;
                case K_3:
                    appendBuffer("3");
                    break;
                case K_4:
                    appendBuffer("4");
                    break;
                case K_5:
                    appendBuffer("5");
                    break;
                case K_6:
                    appendBuffer("6");
                    break;
                case K_7:
                    appendBuffer("7");
                    break;
                case K_8:
                    appendBuffer("8");
                    break;
                case K_9:
                    appendBuffer("9");
                    break;

                case K_1000:
                    appendBuffer("000");
                    break;

                case K_KA:
                	appendBuffer("(");
                	break;
                case K_KZ:
                	appendBuffer(")");
                	break;
                    
                case K_DIV:
                case K_MUL:
                case K_PLUS:
                case K_MIN:
                case K_EQ:
                    onCalc(tok);
                    break;

                case K_VAT:
                    displayVat();				//mwst
                    break;

                case K_DOT:
                    appendBuffer(".");
                    break;

                case K_BACK:
                    dsb.setLength(Math.max(0, dsb.length() - 1));
                    break;

                case K_C:
                    view.writeSideArea("");
                case K_CE:
                    reset();
                    break;

                default:
            }
            String display = dsb.length() > 0 ? dsb.toString() : formatResult(interimResult);
            view.writeTextArea(display);

        } catch (ArithmeticException ae) {
            view.writeTextArea(ae.getMessage());
        } catch (NumberFormatException nfe) {
            view.writeTextArea(nfe.getMessage());
        }
    }



    private void onCalc(Token operator) {
        if (dsb.length() == 0) {
            lastOperator = operator;
        } else {
            double userInput = inputToDouble();

            if (lastOperator == null || lastOperator == K_EQ) {
                interimResult = userInput;
            } else {
                interimResult = calc(lastOperator, interimResult, userInput);
            }

            lastOperator = operator;
            clearInput();
        }
    }

    private double calc(Token operator, double val1, double val2) throws ArithmeticException {
        switch (operator) {
            case K_PLUS:
            	 view.writeSideArea(""+val1+"+"+val2+"="+(val1+val2));
                return val1 + val2;
            case K_MIN:
            	 view.writeSideArea(""+val1+"-"+val2+"="+(val1-val2));
                return val1 - val2;
            case K_MUL:
            	 view.writeSideArea(""+val1+"*"+val2+"="+(val1*val2));
                return val1 * val2;
            case K_DIV:
                if (val2 == 0) {
                    interimResult = 0;
                    throw new ArithmeticException("ERR: div by zero");
                }
                view.writeSideArea(""+val1+"/"+val2+"="+(val1/val2));
                return val1 / val2;
            default:
                throw new Error("Unknown Operation");
        }
    }

    private double inputToDouble() {
        String input = dsb.toString();

        if ( !input.isEmpty() &&!( input.contains("(") || input.contains(")")) ) {
            return Double.parseDouble(input);
        } else {
            return interimResult;
        }
    }

    private String formatResult(double value) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        return df.format(value);
    }

    private void clearInput() {
        dsb.setLength(0);
    }

    private void reset() {
        clearInput();
        interimResult = 0;
        lastOperator = null;
    }

    private void displayVat() {
        String input = dsb.toString();
        double gros = Double.parseDouble(input);
        double vat = gros * VAT_RATE / (100 + VAT_RATE);
        double net = gros - vat;

        DecimalFormat df = new DecimalFormat("#,###.##");
        view.writeSideArea(
                "Brutto:  " + df.format(gros) + "\n" +
                        VAT_RATE + "% MwSt:  " + df.format(vat) + "\n" +
                        "Netto:  " + df.format(net)
        );
    }

    private void appendBuffer( String d ) {
		if( dsb.length() <= CalculatorGUI_Intf.DISPLAY_MAXDIGITS ) {
			
			if(d =="+" || d =="-" || d== "*" || d== "/" || d=="="||d=="." ) {
				String opperator = dsb.toString();
				
				if(!(opperator.endsWith("+")||opperator.endsWith("-") ||opperator.endsWith("*")||opperator.endsWith("/")||opperator.endsWith("."))) {
					dsb.append(d);
				}
				
			}else {
				dsb.append(d);
			}
			
		}
	}

    

}
