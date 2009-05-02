/*
 * CombatPane1.java
 *
 * Created on February 3, 2004, 9:54 AM
 */

package plugin.charactersheet.gui;

import java.awt.Font;

import pcgen.core.Globals;
import pcgen.core.PCStat;
import pcgen.core.PlayerCharacter;
import pcgen.io.exporttoken.AttackToken;
import pcgen.io.exporttoken.InitiativeMiscToken;
import pcgen.io.exporttoken.StatToken;
import pcgen.util.Delta;

/**
 * Confirmed no memory Leaks Dec 10, 2004
 * @author  ddjone3
 */
public class CombatPane2 extends javax.swing.JPanel
{
	private PlayerCharacter pc;

	private static final String BLANK = "";
	private static final String SPACE = " ";
	private static final String EQUALS = "=";
	private static final String PLUS = "+";
	private static final String INITIATIVE = "  Initiative  ";
	private static final String BASE_ATTACK = "Base Attack";
	private static final String TOTAL = "Total";
	private static final String DEX = "Dex";
	private static final String MISC = "Misc";
	private static final String MELEE = "MELEE";
	private static final Font FONT_TEN = new Font("Dialog", 0, 10);
	private static final Font FONT_NINE = new Font("Dialog", 0, 9);

	/** Creates new form CombatPane1 */
	public CombatPane2()
	{
		initComponents();
		setColor();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents()
	{//GEN-BEGIN:initComponents
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel1 = new javax.swing.JPanel();
		attrAbbrev = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		attrAbbrev1 = new javax.swing.JLabel();
		padding4 = new javax.swing.JLabel();
		padding5 = new javax.swing.JLabel();
		padding7 = new javax.swing.JLabel();
		padding10 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		initTotal = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		dexMod = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		miscMod = new javax.swing.JLabel();
		jPanel6 = new javax.swing.JPanel();
		baseAttack = new javax.swing.JLabel();

		setLayout(new java.awt.GridBagLayout());

		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
			0, 0));

		attrAbbrev.setText(INITIATIVE);
		jPanel1.add(attrAbbrev);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		add(jPanel1, gridBagConstraints);

		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
			0, 0));

		attrAbbrev1.setText(BASE_ATTACK);
		jPanel2.add(attrAbbrev1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		add(jPanel2, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new java.awt.Insets(0, 75, 0, 0);
		add(padding4, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new java.awt.Insets(0, 24, 0, 0);
		add(padding5, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new java.awt.Insets(0, 34, 0, 0);
		add(padding7, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new java.awt.Insets(0, 32, 0, 0);
		add(padding10, gridBagConstraints);

		jLabel3.setText(EQUALS);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 1);
		add(jLabel3, gridBagConstraints);

		jLabel4.setText(PLUS);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
		add(jLabel4, gridBagConstraints);

		jLabel10.setFont(FONT_NINE);
		jLabel10.setText(TOTAL);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		add(jLabel10, gridBagConstraints);

		jLabel12.setFont(FONT_NINE);
		jLabel12.setText(DEX);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		add(jLabel12, gridBagConstraints);

		jLabel13.setFont(FONT_NINE);
		jLabel13.setText(MISC);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		add(jLabel13, gridBagConstraints);

		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
			1, 0));

		initTotal.setFont(FONT_TEN);
		initTotal.setText(SPACE);
		jPanel3.add(initTotal);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
		add(jPanel3, gridBagConstraints);

		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
			1, 0));

		dexMod.setFont(FONT_TEN);
		dexMod.setText(SPACE);
		jPanel4.add(dexMod);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
		add(jPanel4, gridBagConstraints);

		jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
			1, 0));

		miscMod.setFont(FONT_TEN);
		miscMod.setText(SPACE);
		jPanel5.add(miscMod);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		add(jPanel5, gridBagConstraints);

		jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,
			1, 0));

		baseAttack.setFont(FONT_TEN);
		baseAttack.setText(SPACE);
		jPanel6.add(baseAttack);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
		add(jPanel6, gridBagConstraints);

	}//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel attrAbbrev;
	private javax.swing.JLabel attrAbbrev1;
	private javax.swing.JLabel baseAttack;
	private javax.swing.JLabel dexMod;
	private javax.swing.JLabel initTotal;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JLabel miscMod;
	private javax.swing.JLabel padding10;
	private javax.swing.JLabel padding4;
	private javax.swing.JLabel padding5;
	private javax.swing.JLabel padding7;

	// End of variables declaration//GEN-END:variables

	/**
	 * Set Color
	 */
	public void setColor()
	{
		setBackground(CharacterPanel.white);
		jPanel1.setBackground(CharacterPanel.bodyDark);
		jPanel2.setBackground(CharacterPanel.bodyDark);
		jPanel3.setBackground(CharacterPanel.white);
		jPanel4.setBackground(CharacterPanel.white);
		jPanel5.setBackground(CharacterPanel.white);
		jPanel6.setBackground(CharacterPanel.white);
		jPanel1.setBorder(new javax.swing.border.LineBorder(
			CharacterPanel.border, 2));
		jPanel2.setBorder(new javax.swing.border.LineBorder(
			CharacterPanel.border, 2));
		jPanel3.setBorder(new javax.swing.border.LineBorder(
			CharacterPanel.border, 2));
		jPanel4.setBorder(new javax.swing.border.LineBorder(
			CharacterPanel.border, 2));
		jPanel5.setBorder(new javax.swing.border.LineBorder(
			CharacterPanel.border, 2));
		jPanel6.setBorder(new javax.swing.border.LineBorder(
			CharacterPanel.border, 2));
	}

	/**
	 * Set PC
	 * @param pc
	 */
	public void setPc(PlayerCharacter pc)
	{
		this.pc = pc;
	}

	/**
	 * Refresh
	 */
	public void refresh()
	{
		baseAttack.setText(AttackToken.getParsedToken(pc, MELEE, BLANK));

		initTotal.setText(Delta.toString(pc.initiativeMod()));
		PCStat dex = Globals.getContext().ref.getAbbreviatedObject(
				PCStat.class, "DEX");
		dexMod.setText(StatToken.getModToken(pc, dex));
		miscMod.setText(Delta.toString(InitiativeMiscToken
			.getInitiativeMiscToken(pc)));
	}

	/**
	 * Destruct
	 */
	public void destruct()
	{
		//Put any code here that is needed to prevent memory leaks when this panel is destroyed
	}
}
