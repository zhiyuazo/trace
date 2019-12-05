package nativeMethod;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public interface Kernel32Interface extends StdCallLibrary
{
   public static class SYSTEMTIME extends Structure
   {
      public short wYear;
      public short wMonth;
      public short wDayOfWeek;
      public short wDay;
      public short wHour;
      public short wMinute;
      public short wSecond;
      public short wMilliseconds;
   }
   String dllname = "kernel32";
   Kernel32Interface INSTANCE = (Kernel32Interface) Native.loadLibrary(dllname,Kernel32Interface.class);
   void GetLocalTime (SYSTEMTIME result);
}