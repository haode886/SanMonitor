# Android Studio运行配置指南

您提到需要添加配置才能运行应用，这是因为Android Studio需要正确的运行配置来启动和部署您的wearOS应用。以下是详细的配置步骤和说明：

## 什么是运行配置

运行配置（Run Configuration）是Android Studio用来确定如何构建、部署和启动应用程序的一组设置。对于无法自动检测配置的项目，您需要手动创建和配置它。

## 添加新的运行配置

按照以下步骤添加适用于wearOS应用的运行配置：

### 步骤1: 打开运行配置对话框

1. 在Android Studio顶部工具栏中，点击运行配置下拉菜单（通常显示为当前项目名称或"Edit Configurations..."）
2. 选择"Edit Configurations..."选项

### 步骤2: 创建新的Android App配置

1. 在左侧面板中，点击加号（+）按钮
2. 选择"Android App"选项
3. 为您的配置命名（例如："SanMonitor"）

### 步骤3: 配置基本设置

在右侧配置面板中，设置以下选项：

#### General选项卡

1. **Module**: 确保选择了正确的应用模块（通常是"app"）
2. **Launch Options**: 
   - 选择"Default Activity"
   - 或选择"Specified Activity"并手动输入`com.yydj.sanmonitor.presentation.MainActivity`

#### Deployment选项卡

1. **Target Device**: 选择您希望运行应用的方式：
   - "Open Select Deployment Target Dialog": 每次运行时选择设备
   - "USB Device": 自动使用已连接的USB设备
   - "Emulator": 自动使用正在运行的模拟器或启动一个新的模拟器

2. **Deploy**: 确保选择"Default APK"或"APK from app bundle"

### 步骤4: 配置wearOS特定设置

1. 点击"Add Android Wear App"按钮
2. 确保选中了您的wearOS应用模块
3. 设置"Method": 
   - 如果使用模拟器：选择"Emulator (via Bluetooth)"
   - 如果使用真实设备：选择"Real device (via Bluetooth)"或"Real device (via USB)"

### 步骤5: 保存配置

1. 点击"Apply"按钮保存您的设置
2. 点击"OK"按钮关闭配置对话框

## 运行应用程序

配置完成后，您可以通过以下方式运行应用：

1. 确保您的wearOS模拟器正在运行，或者已连接真实的wearOS设备
2. 在Android Studio顶部工具栏中，确保选择了您刚刚创建的运行配置
3. 点击绿色的运行按钮（三角形图标）

## 常见问题与解决方案

### 问题1: 找不到MainActivity

如果配置时找不到MainActivity，请确保：
- 您的项目已成功同步和构建
- MainActivity.kt文件存在且包含正确的包名和类名
- 在AndroidManifest.xml中正确声明了MainActivity

### 问题2: 无法选择wearOS设备

如果看不到可用的wearOS设备：
- 确保您已创建并启动了wearOS模拟器
- 如果使用真实设备，确保已启用开发者选项和USB调试
- 检查ADB连接状态：在终端中运行`adb devices`命令

### 问题3: 运行时出现错误

如果运行时出现错误：
- 检查Android Studio底部的"Run"窗口获取详细错误信息
- 执行"Build > Clean Project"和"Build > Rebuild Project"
- 确保Gradle配置正确且项目没有编译错误

## 验证配置

成功配置后，您应该能够：
1. 看到绿色的运行按钮变为可点击状态
2. 成功将应用部署到wearOS模拟器或真实设备
3. 在设备上看到您开发的SanMonitor应用界面

## 额外提示

- 您可以为不同的场景创建多个运行配置（例如：一个用于模拟器测试，一个用于真实设备测试）
- 配置后，您可以使用快捷键`Shift+F10`快速运行应用
- 如果配置丢失或损坏，可以随时重复上述步骤重新创建

如果您在配置过程中遇到任何问题，请参考Android Studio的官方文档或查看IDE中的错误信息获取更多帮助。