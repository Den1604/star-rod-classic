package game.map.editor.ui.info.marker;

import static game.world.entity.EntityInfo.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.alexandriasoftware.swing.JSplitButton;

import game.map.MapObject.MapObjectType;
import game.map.editor.MapEditor;
import game.map.editor.ui.LabelWithTip;
import game.map.editor.ui.MapObjectComboBox;
import game.map.editor.ui.StandardEditableComboBox;
import game.map.editor.ui.SwingGUI;
import game.map.marker.EntityComponent;
import game.map.marker.Marker.MarkerType;
import game.shared.ProjectDatabase;
import game.world.entity.EntityInfo.EntityType;
import game.world.entity.EntityMenuGroup;
import net.miginfocom.swing.MigLayout;
import shared.SwingUtils;
import util.ui.HexTextField;
import util.ui.IntTextField;
import util.ui.StringField;
import util.ui.StringSelectorDialog;

public class MarkerEntityTab extends JPanel implements ActionListener
{
	//TODO
	/*
	private static class FieldPanel extends JPanel
	{
		private FieldPanel(String text, JCheckBox cb, JComponent comp)
		{
			setLayout(new MigLayout("fillx, ins 0", "[][grow][70%]"));
			add(cb);
			add(new JLabel(text));
			add(comp, "growx");
		}
	
		private FieldPanel(String text, JCheckBox cb, JComponent comp, JButton but)
		{
			setLayout(new MigLayout("fillx, ins 0", "[][grow][][]"));
			add(cb);
			add(new JLabel(text));
			add(comp, "w 61%!");
			add(but, "w 7%!");
		}
	
		private FieldPanel(String text, JCheckBox cb, JComponent comp, JButton but1, JButton but2)
		{
			setLayout(new MigLayout("fillx, ins 0", "[][grow][70%]"));
			add(cb);
			add(new JLabel(text));
			add(comp, "growx, pushx, split 3");
			add(but1);
			add(but2);
		}
	}
	*/

	private static class MarkerFieldPanel<T extends JComponent> extends JPanel
	{
		private final LabelWithTip lbl;
		private final T comp;

		public MarkerFieldPanel(T comp, String labelText)
		{
			this(comp, labelText, null);
		}

		public MarkerFieldPanel(T comp, String labelText, int size)
		{
			this(comp, labelText, null, size);
		}

		public MarkerFieldPanel(T comp, String labelText, String tooltip)
		{
			this(comp, labelText, tooltip, 50);
		}

		public MarkerFieldPanel(T comp, String labelText, String tooltip, int size)
		{
			setLayout(new MigLayout("fill, ins 0"));
			lbl = new LabelWithTip(labelText, 12, tooltip);
			this.comp = comp;

			add(lbl, "w 28%!");
			if (0 < size && size < 100) {
				add(comp, "w " + size + "%!");
				add(new JPanel(), "pushx"); // dummy
			}
			else
				add(comp, "growx, pushx");
		}

		public void setLabelTip(String tip)
		{
			lbl.updateTip(tip);
		}

		public T child()
		{
			return comp;
		}
	}

	private static class MarkerFieldArrayPanel extends JPanel
	{
		private final LabelWithTip lbl;
		private final JComponent[] components;

		public MarkerFieldArrayPanel(JComponent[] components, String[] constraints, String labelText)
		{
			this(components, constraints, labelText, null);
		}

		public MarkerFieldArrayPanel(JComponent[] components, String[] constraints, String labelText, String tooltip)
		{
			setLayout(new MigLayout("fill, ins 0"));
			lbl = new LabelWithTip(labelText, 12, tooltip);
			this.components = components;

			add(lbl, "w 28%!");
			for (int i = 0; i < components.length; i++)
				add(components[i], constraints[i]);
		}

		public void setLabelTip(String tip)
		{
			lbl.updateTip(tip);
		}

		public JComponent child(int index)
		{
			return components[index];
		}
	}

	private final MarkerInfoPanel parent;

	private JSplitButton entityTypeButton;

	private JCheckBox cbHasCallback;

	private JCheckBox cbHasFlag;
	private StandardEditableComboBox flagNameBox;

	private JCheckBox cbHasSpawnFlag;
	private StandardEditableComboBox spawnFlagNameBox;

	private MarkerFieldPanel<StandardEditableComboBox> itemSpawnPanel;

	private MarkerFieldPanel<MapObjectComboBox> modelPanel;
	private MarkerFieldPanel<MapObjectComboBox> colliderPanel;
	private MarkerFieldPanel<MapObjectComboBox> destMarkerPanel;
	private MarkerFieldPanel<MapObjectComboBox> pathMarkerPanel;

	private MarkerFieldPanel<IntTextField> launchHeightPanel;
	private MarkerFieldPanel<IntTextField> launchTimePanel;
	private MarkerFieldPanel<IntTextField> signAnglePanel;

	private JCheckBox cbHasAreaFlag;
	private HexTextField areaFlagField;
	private MarkerFieldArrayPanel mapVarPanel;

	private MarkerFieldPanel<MapObjectComboBox> pipeEntryPanel;
	private MarkerFieldPanel<StringField> destMapPanel;
	private JPanel destEntryPanel;
	private StringField destEntryField;
	private JCheckBox cbDestUseIndex;

	private JCheckBox cbHasItem;
	private StandardEditableComboBox itemNameBox;

	private String openBoxChooserDialog(JComboBox<?> box, String title)
	{
		List<String> items = new ArrayList<>();

		for (int i = 0; i < box.getItemCount(); i++)
			items.add((String) box.getItemAt(i));

		StringSelectorDialog chooser = new StringSelectorDialog(items);
		SwingUtils.showModalDialog(chooser, title);
		if (chooser.isResultAccepted())
			return chooser.getValue();
		else
			return null;
	}

	public MarkerEntityTab(MarkerInfoPanel parent)
	{
		this.parent = parent;

		JPopupMenu entityTypePopupMenu = new JPopupMenu();
		SwingGUI.instance().registerPopupMenu(entityTypePopupMenu);
		createPopupMenu(entityTypePopupMenu);

		//	setLayout(new MigLayout("fillx, hidemode 3, ins 0, wrap"));
		//	add(SwingUtils.getLabel("Entity Settings", 14), "growx, gapbottom 4");

		entityTypeButton = new JSplitButton("EntityType");
		entityTypeButton.setPopupMenu(entityTypePopupMenu);
		entityTypeButton.setAlwaysPopup(true);
		SwingUtils.addBorderPadding(entityTypeButton);
		entityTypeButton.setHorizontalAlignment(SwingConstants.LEFT);

		//TODO
		/*
		add(new JLabel("Entity Type"), "split 2, growx");
		add(entityTypeButton, "w 70%!");

		addItemPanel();

		addGameFlagPanel();
		addAreaFlagPanel();
		addMapVarPanel();
		addScriptPanel();
		
		addIndexPanel();
		addStylePanel();
		
		addModelPanel();
		addColliderPanel();
		addTargetPanel();
		
		addEntryPanel();
		addPathsPanel();
		addAnglePanel();
		addLaunchDistPanel();
		addSpawnModePanel();

		add(new JLabel(), "pushy");
		*/

		cbHasCallback = new JCheckBox(" Has script callback");
		cbHasCallback.addActionListener((e) -> MapEditor.execute(
			parent.getData().entityComponent.hasCallback.mutator(cbHasCallback.isSelected())));

		cbHasFlag = new JCheckBox(" Sets flag");
		cbHasFlag.addActionListener((e) -> MapEditor.execute(
			parent.getData().entityComponent.hasFlag.mutator(cbHasFlag.isSelected())));

		flagNameBox = new StandardEditableComboBox((s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.flagName.mutator(s));
		}, ProjectDatabase.getModFlagList());

		cbHasSpawnFlag = new JCheckBox(" Spawn flag");
		cbHasSpawnFlag.addActionListener((e) -> MapEditor.execute(
			parent.getData().entityComponent.hasSpawnFlag.mutator(cbHasSpawnFlag.isSelected())));

		spawnFlagNameBox = new StandardEditableComboBox((s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.spawnFlagName.mutator(s));
		}, ProjectDatabase.getModFlagList());

		cbHasItem = new JCheckBox(" Has item");
		cbHasItem.addActionListener((e) -> MapEditor.execute(
			parent.getData().entityComponent.hasItem.mutator(cbHasItem.isSelected())));

		itemNameBox = new StandardEditableComboBox((s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.itemName.mutator(s));
		}, ProjectDatabase.getItemNames());

		StandardEditableComboBox itemSpawnField = new StandardEditableComboBox((s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.itemSpawnMode.mutator(s));
		}, ProjectDatabase.getFromNamespace("ItemSpawnMode").getValues());
		itemSpawnPanel = new MarkerFieldPanel<>(itemSpawnField, "Spawn Mode");

		cbHasAreaFlag = new JCheckBox(" Area flag");
		cbHasAreaFlag.addActionListener((e) -> MapEditor.execute(
			parent.getData().entityComponent.hasAreaFlag.mutator(cbHasAreaFlag.isSelected())));

		areaFlagField = new HexTextField(2, (v) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.areaFlagIndex.mutator(v));
		});
		areaFlagField.setHorizontalAlignment(JTextField.CENTER);

		HexTextField mapVarField = new HexTextField(1, (v) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.mapVarIndex.mutator(v));
		});
		mapVarField.setHorizontalAlignment(JTextField.CENTER);

		JCheckBox cbAutoMapVar = new JCheckBox(" Automatic");
		cbAutoMapVar.addActionListener((e) -> MapEditor.execute(
			parent.getData().entityComponent.autoMapVar.mutator(cbAutoMapVar.isSelected())));

		mapVarPanel = new MarkerFieldArrayPanel(
			new JComponent[] { mapVarField, cbAutoMapVar },
			new String[] { "w 25%!", "growx, pushx" },
			"Map Var Index");

		MapObjectComboBox modelBox = new MapObjectComboBox(MapObjectType.MODEL, (s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.modelName.mutator(s));
		});
		modelPanel = new MarkerFieldPanel<>(modelBox, "Model", 999);

		MapObjectComboBox colliderBox = new MapObjectComboBox(MapObjectType.COLLIDER, (s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.colliderName.mutator(s));
		});
		colliderPanel = new MarkerFieldPanel<>(colliderBox, "Collider",
			"(optional) Invisible wall tied to this entity.", 999);

		MapObjectComboBox destMarkerBox = new MapObjectComboBox(MarkerType.Position, (s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.targetMarker.mutator(s));
		});
		destMarkerPanel = new MarkerFieldPanel<>(destMarkerBox, "Dest Position",
			"Position this entity will launch the player to.", 999);

		MapObjectComboBox pathMarkerBox = new MapObjectComboBox(MarkerType.Position, (s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.pathMarker.mutator(s));
		});
		pathMarkerPanel = new MarkerFieldPanel<>(pathMarkerBox, "Move Path",
			"Path for this entity to move along.", 999);

		IntTextField launchHeightField = new IntTextField((v) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.springLaunchHeight.mutator(v));
		});
		launchHeightField.setHorizontalAlignment(JTextField.CENTER);
		launchHeightPanel = new MarkerFieldPanel<>(launchHeightField, "Launch Height", 25);

		IntTextField launchTimeField = new IntTextField((v) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.springLaunchArc.mutator(v));
		});
		launchTimeField.setHorizontalAlignment(JTextField.CENTER);
		launchTimePanel = new MarkerFieldPanel<>(launchTimeField, "Arc Height", 25);

		IntTextField angleField = new IntTextField((v) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.signAngle.mutator(v));
		});
		angleField.setHorizontalAlignment(JTextField.CENTER);
		signAnglePanel = new MarkerFieldPanel<>(angleField, "Sign Angle", 25);

		StringField destMapField = new StringField((s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.gotoMap.mutator(s));
		});
		destMapField.setHorizontalAlignment(JTextField.LEADING);
		destMapPanel = new MarkerFieldPanel<>(destMapField, "Dest Map");

		destEntryField = new StringField((s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.gotoEntry.mutator(s));
		});
		destEntryField.setHorizontalAlignment(JTextField.LEADING);

		cbDestUseIndex = new JCheckBox("Interpret as ID");
		cbDestUseIndex.setToolTipText("Should the name to the left be interpreted as a marker name or an ID number?");
		cbDestUseIndex.addActionListener((e) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.useDestMarkerID.mutator(cbDestUseIndex.isSelected()));
		});

		destEntryPanel = new JPanel(new MigLayout("fill, ins 0"));
		destEntryPanel.add(SwingUtils.getLabel("Dest Marker", 12), "w 28%!");
		destEntryPanel.add(destEntryField, "growx, pushx");
		destEntryPanel.add(cbDestUseIndex, "growx");

		MapObjectComboBox pipeEntryBox = new MapObjectComboBox(MarkerType.Entry, (s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.pipeEntry.mutator(s));
		});
		pipeEntryPanel = new MarkerFieldPanel<>(pipeEntryBox, "Map Entry", "Entry should be placed 35 units above the ground.");

		JPanel contents = new JPanel(new MigLayout("fillx, ins 0, hidemode 3, wrap"));

		String layoutConst = "sgy row, growx";

		contents.add(new JLabel("Type"), "split 2, w 28%!");
		contents.add(entityTypeButton, "w 50%!");

		contents.add(pipeEntryPanel, layoutConst);

		contents.add(cbHasAreaFlag, "split 2, w 28%!");
		contents.add(areaFlagField, "w 25%!");

		contents.add(mapVarPanel, layoutConst);

		contents.add(cbHasFlag, "split 2, w 28%!");
		contents.add(flagNameBox, "growx");

		contents.add(cbHasSpawnFlag, "split 2, w 28%!");
		contents.add(spawnFlagNameBox, "growx");

		contents.add(cbHasItem, "split 2, w 28%!");
		contents.add(itemNameBox, "w 50%!");
		contents.add(itemSpawnPanel, "growx");

		contents.add(modelPanel, layoutConst);
		contents.add(colliderPanel, layoutConst);
		contents.add(destMarkerPanel, layoutConst);
		contents.add(pathMarkerPanel, layoutConst);

		contents.add(launchHeightPanel, layoutConst);
		contents.add(launchTimePanel, layoutConst);
		contents.add(signAnglePanel, layoutConst);

		contents.add(destMapPanel, layoutConst);
		contents.add(destEntryPanel, layoutConst);

		contents.add(cbHasCallback, layoutConst);

		setLayout(new MigLayout("fill, ins n 16 n 16"));
		add(contents, "growx, gapbottom push");
	}

	//TODO
	/*
	private void addItemPanel()
	{
		cbHasItem = new JCheckBox();
		cbHasItem.addActionListener((e) -> MapEditor.execute(
			parent.getData().entityComponent.itemName.enabler(cbHasItem.isSelected())
		));

		itemBox = new StandardEditableComboBox((s) -> {
			if (parent.ignoreEvents() || parent.getData() == null)
				return;
			MapEditor.execute(parent.getData().entityComponent.itemName.mutator(s));
		}, ProjectDatabase.getItemNames());

		selectItemButton = new JButton("~");
		selectItemButton.addActionListener((e) -> {
			String newName = openBoxChooserDialog(itemBox, "Choose Item");
			if (newName != null)
				MapEditor.execute(parent.getData().entityComponent.itemName.mutator(newName));
		});
		SwingUtils.addBorderPadding(selectItemButton);

		itemPanel = new FieldPanel("Item", cbHasItem, itemBox, selectItemButton);
		add(itemPanel, "growx");
	}
	*/

	private JPopupMenu createPopupMenu(JPopupMenu popupMenu)
	{
		for (EntityMenuGroup group : EntityMenuGroup.values()) {
			if (group == EntityMenuGroup.Hidden)
				continue;

			JMenu groupMenu = new JMenu(group.toString() + "      ");
			popupMenu.add(groupMenu);

			for (EntityType type : EntityType.values()) {
				if (type.menuGroup != group)
					continue;

				JMenuItem typeItem = new JMenuItem(type.toString());
				typeItem.setPreferredSize(new Dimension(144, typeItem.getPreferredSize().height));
				typeItem.setActionCommand(type.name());
				typeItem.addActionListener(this);
				groupMenu.add(typeItem);
			}
		}

		return popupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (parent.ignoreEvents())
			return;

		// respond to changes from the popup menu
		String cmd = e.getActionCommand();
		EntityType type = EntityType.valueOf(cmd);
		MapEditor.execute(parent.getData().entityComponent.type.mutator(type));
	}

	public void onSetData()
	{
		modelPanel.child().updateNames(parent.getData().entityComponent.modelName.get());
		colliderPanel.child().updateNames(parent.getData().entityComponent.colliderName.get());
		destMarkerPanel.child().updateNames(parent.getData().entityComponent.targetMarker.get());
		pathMarkerPanel.child().updateNames(parent.getData().entityComponent.pathMarker.get());
		pipeEntryPanel.child().updateNames(parent.getData().entityComponent.pipeEntry.get());
	}

	//TODO
	/*
	private void updateBoxPanel(MapKey key, JPanel panel, JCheckBox cb,
		StandardEditableComboBox ui, EditableField<String> field)
	{
		EntityComponent data = parent.getData().entityComponent;
		EntityParam param = data.type.get().getParam(key);
	
		if (param != null) {
			boolean cbEnabled;
			boolean uiEnabled;
	
			if (param.required) {
				cbEnabled = false;
				uiEnabled = true;
			}
			else {
				cbEnabled = true;
				uiEnabled = field.isEnabled();
			}
	
			cb.setEnabled(cbEnabled);
			cb.setSelected(uiEnabled);
			ui.setEnabled(uiEnabled);
	
			ui.setSelectedItem(field.get());
			panel.setVisible(true);
		}
		else {
			panel.setVisible(false);
		}
	}
	
	private void updateMapObjPanel(MapKey key, JPanel panel, JCheckBox cb,
		MapObjectComboBox ui, EditableField<String> field)
	{
		EntityComponent data = parent.getData().entityComponent;
		EntityParam param = data.type.get().getParam(key);
	
		if (param != null) {
			boolean cbEnabled;
			boolean uiEnabled;
	
			if (param.required) {
				cbEnabled = false;
				uiEnabled = true;
			}
			else {
				cbEnabled = true;
				uiEnabled = field.isEnabled();
			}
	
			cb.setEnabled(cbEnabled);
			cb.setSelected(uiEnabled);
			ui.setEnabled(uiEnabled);
	
			ui.setSelectedItem(field.get());
			panel.setVisible(true);
		}
		else {
			panel.setVisible(false);
		}
	}
	
	private void updateStringPanel(MapKey key, JPanel panel, JCheckBox cb,
		StringField ui, EditableField<String> field)
	{
		EntityComponent data = parent.getData().entityComponent;
		EntityParam param = data.type.get().getParam(key);
	
		if (param != null) {
			boolean cbEnabled;
			boolean uiEnabled;
	
			if (param.required) {
				cbEnabled = false;
				uiEnabled = true;
			}
			else {
				cbEnabled = true;
				uiEnabled = field.isEnabled();
			}
	
			cb.setEnabled(cbEnabled);
			cb.setSelected(uiEnabled);
			ui.setEnabled(uiEnabled);
	
			ui.setText(field.get());
			panel.setVisible(true);
		}
		else {
			panel.setVisible(false);
		}
	}
	
	private void updateIntPanel(MapKey key, JPanel panel, JCheckBox cb,
		IntTextField ui, EditableField<Integer> field)
	{
		EntityComponent data = parent.getData().entityComponent;
		EntityParam param = data.type.get().getParam(key);
	
		if (param != null) {
			boolean cbEnabled;
			boolean uiEnabled;
	
			if (param.required) {
				cbEnabled = false;
				uiEnabled = true;
			}
			else {
				cbEnabled = true;
				uiEnabled = field.isEnabled();
			}
	
			cb.setEnabled(cbEnabled);
			cb.setSelected(uiEnabled);
			ui.setEnabled(uiEnabled);
	
			ui.setValue(field.get());
			panel.setVisible(true);
		}
		else {
			panel.setVisible(false);
		}
	}
	*/

	public void onUpdateFields()
	{
		boolean visible;
		EntityComponent data = parent.getData().entityComponent;
		EntityType type = data.type.get();
		int typeFlags = type.fieldFlags;

		entityTypeButton.setText(type.toString());

		visible = ((typeFlags & FIELD_HAS_CALLBACK) != 0);
		cbHasCallback.setVisible(visible);
		if (visible)
			cbHasCallback.setSelected(data.hasCallback.get());

		boolean hasFlagVisible = ((typeFlags & FIELD_HAS_FLAG) != 0);
		boolean flagVisible = ((typeFlags & FIELD_FLAG) != 0);

		cbHasFlag.setEnabled(hasFlagVisible);
		cbHasFlag.setVisible(flagVisible);
		cbHasFlag.setSelected(!hasFlagVisible || data.hasFlag.get());

		flagNameBox.setVisible(flagVisible);
		flagNameBox.setEnabled(!hasFlagVisible || data.hasFlag.get());
		if (flagVisible)
			flagNameBox.setSelectedItem(data.flagName.get());

		boolean hasSpawnFlagVisible = ((typeFlags & FIELD_HAS_SPAWN_FLAG) != 0);
		boolean spawnFlagVisible = ((typeFlags & FIELD_SPAWN_FLAG) != 0);

		cbHasSpawnFlag.setEnabled(hasSpawnFlagVisible);
		cbHasSpawnFlag.setVisible(spawnFlagVisible);
		cbHasSpawnFlag.setSelected(!hasSpawnFlagVisible || data.hasSpawnFlag.get());

		spawnFlagNameBox.setVisible(spawnFlagVisible);
		spawnFlagNameBox.setEnabled(!hasSpawnFlagVisible || data.hasSpawnFlag.get());
		if (spawnFlagVisible)
			spawnFlagNameBox.setSelectedItem(data.spawnFlagName.get());

		boolean hasItemVisible = ((typeFlags & FIELD_HAS_ITEM) != 0);
		boolean itemVisible = ((typeFlags & FIELD_ITEM) != 0);

		cbHasItem.setEnabled(hasItemVisible);
		cbHasItem.setVisible(itemVisible);
		cbHasItem.setSelected(!hasItemVisible || data.hasItem.get());

		itemNameBox.setVisible(itemVisible);
		itemNameBox.setEnabled(!hasItemVisible || data.hasItem.get());
		if (itemVisible)
			itemNameBox.setSelectedItem(data.itemName.get());

		visible = ((typeFlags & FIELD_ITEM_SPAWN) != 0);
		itemSpawnPanel.setVisible(visible);
		itemSpawnPanel.child().setEnabled(!hasItemVisible || data.hasItem.get());
		if (visible)
			itemSpawnPanel.child().setSelectedItem(data.itemSpawnMode.get());

		visible = ((typeFlags & FIELD_MODEL) != 0);
		modelPanel.setVisible(visible);
		if (visible) {
			modelPanel.child().setText(data.modelName.get());

			if (type == EntityType.HiddenPanel)
				modelPanel.setLabelTip("Used to choose a texture for the hidden panel.");
			else if (type == EntityType.BoardedFloor)
				modelPanel.setLabelTip("(optional) Floor model tied to this entity.");
		}

		visible = ((typeFlags & FIELD_COLLIDER) != 0);
		colliderPanel.setVisible(visible);
		if (visible)
			colliderPanel.child().setText(data.colliderName.get());

		visible = ((typeFlags & FIELD_TARGET_MARKER) != 0);
		destMarkerPanel.setVisible(visible);
		if (visible)
			destMarkerPanel.child().setText(data.targetMarker.get());

		visible = ((typeFlags & FIELD_PATH_MARKER) != 0);
		pathMarkerPanel.setVisible(visible);
		if (visible)
			pathMarkerPanel.child().setText(data.pathMarker.get());

		boolean hasAreaFlagVisible = ((typeFlags & FIELD_HAS_AREA_FLAG) != 0);
		boolean areaFlagVisible = ((typeFlags & FIELD_AREA_FLAG) != 0);

		cbHasAreaFlag.setEnabled(hasAreaFlagVisible);
		cbHasAreaFlag.setVisible(areaFlagVisible);
		cbHasAreaFlag.setSelected(!hasAreaFlagVisible || data.hasAreaFlag.get());

		areaFlagField.setVisible(areaFlagVisible);
		areaFlagField.setEnabled(!hasAreaFlagVisible || data.hasAreaFlag.get());
		if (areaFlagVisible)
			areaFlagField.setValue(data.areaFlagIndex.get());

		visible = ((typeFlags & FIELD_MAP_VAR) != 0);
		mapVarPanel.setVisible(visible);
		if (visible) {
			HexTextField tf = (HexTextField) mapVarPanel.child(0);
			JCheckBox cb = (JCheckBox) mapVarPanel.child(1);
			tf.setValue(data.mapVarIndex.get());
			tf.setEnabled(!data.autoMapVar.get());
			cb.setSelected(data.autoMapVar.get());
		}

		visible = ((typeFlags & FIELD_LAUNCH_H) != 0);
		launchHeightPanel.setVisible(visible);
		if (visible)
			launchHeightPanel.child().setValue(data.springLaunchHeight.get());

		visible = ((typeFlags & FIELD_LAUNCH_T) != 0);
		launchTimePanel.setVisible(visible);
		if (visible)
			launchTimePanel.child().setValue(data.springLaunchArc.get());

		visible = ((typeFlags & FIELD_ANGLE) != 0);
		signAnglePanel.setVisible(visible);
		if (visible)
			signAnglePanel.child().setValue(data.signAngle.get());

		visible = ((typeFlags & FIELD_DEST_MAP) != 0);
		destMapPanel.setVisible(visible);
		if (visible)
			destMapPanel.child().setText(data.gotoMap.get());

		visible = ((typeFlags & FIELD_DEST_ENTRY) != 0);
		destEntryPanel.setVisible(visible);
		if (visible) {
			destEntryField.setText(data.gotoEntry.get());
			cbDestUseIndex.setSelected(data.useDestMarkerID.get());
		}

		visible = ((typeFlags & FIELD_PIPE_ENTRY) != 0);
		pipeEntryPanel.setVisible(visible);
		if (visible)
			pipeEntryPanel.child().setText(data.pipeEntry.get());
	}
}