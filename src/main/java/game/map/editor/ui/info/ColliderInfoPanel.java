package game.map.editor.ui.info;

import static game.map.hit.Collider.*;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import app.SwingUtils;
import game.map.MapObject.HitType;
import game.map.MapObject.SetObjectName;
import game.map.editor.MapEditor;
import game.map.editor.MapInfoPanel;
import game.map.editor.ui.SwingGUI;
import game.map.hit.Collider;
import game.shared.ProjectDatabase;
import game.shared.ProjectDatabase.EnumComboBox;
import net.miginfocom.swing.MigLayout;
import util.ui.HexTextField;
import util.ui.NameTextField;

public class ColliderInfoPanel extends MapInfoPanel<Collider>
{
	private JTabbedPane tabs;

	private NameTextField nameField;
	private JLabel idLabel;

	private HexTextField flagsField;

	private JCheckBox cbIgnorePlayer;
	private JCheckBox cbIgnoreNPC;
	private JCheckBox cbIgnoreShell;

	private EnumComboBox surfaceTypeBox;

	public ColliderInfoPanel()
	{
		super(false);

		tabs = new JTabbedPane();
		tabs.addTab("Collider", createGeneralTab());

		setLayout(new MigLayout("fill, ins 0"));
		add(tabs, "span, grow, pushy");
	}

	private JPanel createGeneralTab()
	{
		idLabel = new JLabel();

		nameField = new NameTextField((name) -> {
			MapEditor.execute(new SetObjectName(getData(), name));
		});

		flagsField = new HexTextField((flags) -> {
			MapEditor.execute(getData().flags.mutator(flags));
		});

		cbIgnorePlayer = new JCheckBox("Ignore Player and Partner");
		cbIgnoreNPC = new JCheckBox("Ignore NPCs and Items");
		cbIgnoreShell = new JCheckBox("Ignore Kooper's Shell");

		cbIgnorePlayer.addActionListener((e) -> {
			flipBit(cbIgnorePlayer, IGNORE_PLAYER_BIT);
		});

		cbIgnoreNPC.addActionListener((e) -> {
			flipBit(cbIgnoreNPC, IGNORE_NPC_BIT);
		});

		cbIgnoreShell.addActionListener((e) -> {
			flipBit(cbIgnoreShell, IGNORE_SHELL_BIT);
		});

		surfaceTypeBox = new EnumComboBox(ProjectDatabase.getFromNamespace("Surface"));

		surfaceTypeBox.addActionListener((e) -> {
			if (ignoreEvents())
				return;
			MapEditor.execute(getData().surface.mutator(surfaceTypeBox.getSelectedValue()));
		});
		surfaceTypeBox.setMaximumRowCount(surfaceTypeBox.getItemCount());
		SwingUtils.addBorderPadding(surfaceTypeBox);

		JPanel tab = new JPanel();
		tab.setLayout(new MigLayout("fillx, ins n 16 0 16, wrap 2", "[15%][grow]"));

		tab.add(new JLabel("Name"));
		tab.add(nameField, "split 3, w 50%!");
		tab.add(new JLabel("ID:", SwingConstants.RIGHT), "pushx, growx");
		tab.add(idLabel, "w 10%!");

		tab.add(new JLabel("Flags"));
		tab.add(flagsField, "w 50%!");

		tab.add(cbIgnorePlayer, "skip 1, growx");
		tab.add(cbIgnoreNPC, "skip 1, growx");
		tab.add(cbIgnoreShell, "skip 1, growx");

		tab.add(new JLabel("Surface"), "gaptop 8");
		tab.add(surfaceTypeBox, "w 50%!");

		return tab;
	}

	private void flipBit(JCheckBox cb, int bit)
	{
		int flagBits = getData().flags.get();

		if (cb.isSelected())
			flagBits |= bit;
		else
			flagBits &= ~bit;

		MapEditor.execute(getData().flags.mutator(flagBits));
	}

	@Override
	public void afterSetData(Collider collider)
	{
		if (getData() == null)
			return;

		nameField.setText(getData().getName());

		if (getData().getType() == HitType.ROOT) {
			nameField.setEnabled(false);

			flagsField.setEnabled(false);
			flagsField.setText("00000000");

			idLabel.setText("N/A");
		}
		else {
			nameField.setEnabled(true);

			flagsField.setEnabled(true);
			flagsField.setText(String.format("%08X", getData().flags.get()));

			idLabel.setText(String.format("0x%X", getData().getNode().getTreeIndex()));
		}
	}

	@Override
	public void updateFields(Collider collider, String tag)
	{
		if (getData() == collider) {
			nameField.setValue(collider.getName());

			int flagBits = collider.flags.get();
			flagsField.setValue(flagBits);

			cbIgnorePlayer.setSelected((flagBits & IGNORE_PLAYER_BIT) != 0);
			cbIgnoreNPC.setSelected((flagBits & IGNORE_NPC_BIT) != 0);
			cbIgnoreShell.setSelected((flagBits & IGNORE_SHELL_BIT) != 0);

			surfaceTypeBox.selectByValue(collider.surface.get());
		}

		SwingGUI.instance().repaintObjectPanel();
	}
}
